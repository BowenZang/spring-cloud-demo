package cn.markzang.springcloud.service.impl;

import cn.markzang.springcloud.command.UserCommand;
import cn.markzang.springcloud.pojo.User;
import cn.markzang.springcloud.service.IConsumerService;
import com.netflix.hystrix.HystrixCommand.Setter;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.ObservableExecutionMode;
import com.netflix.hystrix.contrib.javanica.command.AsyncResult;
import com.netflix.hystrix.exception.HystrixBadRequestException;
import java.util.concurrent.Future;
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
	
	
	
}
