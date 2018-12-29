package cn.markzang.springcloud.service.impl;

import cn.markzang.springcloud.command.UserCommand;
import cn.markzang.springcloud.pojo.User;
import cn.markzang.springcloud.service.IConsumerService;
import com.netflix.hystrix.HystrixCommand.Setter;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.annotation.ObservableExecutionMode;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheKey;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheRemove;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import com.netflix.hystrix.contrib.javanica.command.AsyncResult;
import com.netflix.hystrix.exception.HystrixBadRequestException;
import java.util.List;
import java.util.concurrent.Future;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import rx.Observable;
import rx.Observable.OnSubscribe;
import rx.Subscriber;

/**
 * **** 该 java 文件的说明 ****
 *
 * @author BowenZang
 * @since 2018年12月19日
 */
@Service
public class ConsumerServiceImpl implements IConsumerService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	/**
	 * <h3>
	 *     	指定服务降级方法
	 * </h3>
	 * <p>
	 *     	服务降级的方法要和被注解方法参数相同（后续验证） TODO
	 * </p>
	 *
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "helloForBack")
	@Override
	public String helloConsumer() {
		return restTemplate.getForEntity("http://client/", String.class).getBody();
	}
	
	private String helloForBack() {
		return "error";
	}
	
	@HystrixCommand
	@Override
	public String users(String id) {
		Future<User> userFuture = new AsyncResult<User>() {
			@Override
			public User invoke() {
				return restTemplate.getForObject("", User.class, id);
			}
		};
		return userFuture.toString();
	}
	
	@Override
	public String users() {
		User user = new UserCommand(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("execute test")), restTemplate, 1L).execute();
		Future<User> userFuture = new UserCommand(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("execute queue")), restTemplate, 1L).queue();
		Observable<User> observe = new UserCommand(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("execute observe")), restTemplate, 1L).observe();
		Observable<User> toObservable = new UserCommand(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("execute toObservable")) ,restTemplate, 1L).toObservable();
		return null;
	}

	// 表示用observe()方式执行
	@HystrixCommand(observableExecutionMode = ObservableExecutionMode.EAGER)
	// 表示用toObservable()方式执行
//	@HystrixCommand(observableExecutionMode = ObservableExecutionMode.LAZY)
	public Observable<User> getUserById(String id) {
		return Observable.create(new OnSubscribe<User>() {
			@Override
			public void call(Subscriber<? super User> subscriber) {
				try {
					if (!subscriber.isUnsubscribed()) {
						User user = restTemplate.getForObject("", User.class, id);
						subscriber.onNext(user);
						subscriber.onCompleted();
					}
				} catch (Exception e) {
					subscriber.onError(e);
				}
			}
		});
	}
	
	@HystrixCommand(ignoreExceptions = {HystrixBadRequestException.class})
	public String exceptionDemo() {
		return null;
	}
	
	@HystrixCommand(fallbackMethod = "getUserByIdTwo")
	public User getUserByIdOne(String userId) {
		return new User("first user");
	}
	
	@HystrixCommand(fallbackMethod = "getUserByIdThree")
	public User getUserByIdTwo(String userId) {
		// 此处可能是另外一个网络请求，所以也有可能失败
		return new User("second user");
	}
	
	/**
	 * <h3>
	 *     可以捕获具体异常，然后针对不通的异常信息进行处理
	 * </h3>
	 *
	 * @param userId
	 * @param e
	 * @return
	 */
	private User getUserByIdThree(String userId, Throwable e) {
		assert "getUserByIdMethod command failed".equals(e.getMessage());
		return new User("third user");
	}
	
	/**
	 * <h3>
	 *		注解方式分别指定命令名称、分组和线程池划分
	 * </h3>
	 *
	 * @param userId
	 * @return
	 */
	@HystrixCommand(commandKey = "getUserById", groupKey = "UserGroup", threadPoolKey = "getUserThread")
	public User getUserByIdFourth(String userId) {
		return restTemplate.getForObject("http://client/user/{1}", User.class, userId);
	}
	
	/**
	 * <h3>
	 *		设置请求缓存
	 * </h3>
	 * <p>
	 *     	@CacheResult 该注解用来标记，请求命令返回数据应该被缓存，必须和@HystrixCommand一起使用
	 *     		cacheKeyMethod用来指定生成缓存key的方法（类似服务降级fallBackMethod）
	 * 	   	@CacheKey 该注解用在参数上，用来指定该参数为缓存值，可用value指定key名，但要注意，此注解优先级比设置cacheKeyMethod低
	 * 	   		还可以直接用在实体类参数上，指定里面的属性作为key
	 * </p>
	 *
	 * @param userId
	 * @return
	 */
	@CacheResult(cacheKeyMethod = "getUserByIdCacheKey")
	@HystrixCommand
	public User getUserByIdFifth(@CacheKey(value = "id") String userId, @CacheKey(value = "id") User user) {
		return restTemplate.getForObject("http://client/user/{1}", User.class, userId);
	}
	
	private Long getUserByIdCacheKey(String userId) {
		return userId == null ? 0L : Long.valueOf(userId);
	}
	
	/**
	 * <h3>
	 *		缓存清理
	 * </h3>
	 * <p>
	 * 		@CacheRemove：缓存清理，必须指定commandKey，指定了命令名才能找到正确的缓存进行清理
	 * 		此外有一个问题，它也必须和@HystrixCommand注解一起使用吗？ TODO
	 * </p>
	 *
	 * @param user
	 */
	@CacheRemove(commandKey = "getUserById")
	@HystrixCommand
	public void updateUser(@CacheKey(value = "id") User user) {
		restTemplate.postForObject("Http://client/users", user, User.class);
	}
	
	/** =======================请求合并======================== */
	
	/**
	 * <h3>
	 * 		根据id查询单个User
	 * </h3>
	 * <p>
	 *     	优点：
	 *     		使用请求合并可以节省请求资源和线程池的开销
	 *     	缺点：
	 *     		使用请求合并会带来额外的延时时间开销，例如愿请求只需要5ms执行，使用请求合并后则至少为15ms才能完成
	 *     	选择请求合并的考虑因素：
	 *     		1、请求命令本身的延迟。如果依赖服务的请求命令本身是一个高延迟的命令，那么可以使用请求合并，
	 *     			因为延迟时间窗的时间消耗显得微不足道了
	 *     		2、延迟时间窗内的并发量。如果一个时间窗内只有1～2个请求，那么这样的依赖服务不适合使用请求合并器。
	 *     			这种情况不但不能提升系统性能，反而会成为系统瓶颈，因为每个请求都需要多消耗一个时间窗才响应。
	 *     			相反，如果一个时间窗内具有很高的并发量，并且服务提供方也实现了批量处理的接口，
	 *     			那么使用请求合并器可以有效的减少网络连接数量并极大的提升系统吞吐量，此时延迟时间窗所增加的消耗就可以忽略不计了
	 * </p>
	 * @param id
	 * @return
	 */
	@HystrixCollapser(batchMethod = "findAll", collapserProperties = {
			@HystrixProperty(name = "timerDelayInmilliseconds", value = "100")
	})
	public User find(Long id) {
		return restTemplate.getForObject("http://client/users/{1}", User.class, id);
	}
	
	/**
	 * <h3>
	 * 		根据ids返回User对象列表，ids为逗号隔开的id集合
	 * </h3>
	 *
	 * @param ids
	 * @return
	 */
	@HystrixCommand
	public List<User> findAll(List<Long> ids) {
		return restTemplate.getForObject("http://client/users?ids={}", List.class, StringUtils.join(ids, ","));
	}
	
}
