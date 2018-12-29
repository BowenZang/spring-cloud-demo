package cn.markzang.springcloud.command;

import cn.markzang.springcloud.pojo.User;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixRequestCache;
import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategyDefault;
import org.springframework.web.client.RestTemplate;

/**
 * 查询用户，GET方式
 *
 * @author BowenZang
 * @since 2018年12月29日
 */
public class UserGetCommand extends HystrixCommand<User> {
	
	private static final HystrixCommandKey GETTER_KEY = HystrixCommandKey.Factory.asKey("CommandName");
	
	private RestTemplate restTemplate;
	
	private Long id;
	
	public UserGetCommand(Setter setter, RestTemplate restTemplate, Long id) {
		super(setter);
		this.restTemplate = restTemplate;
		this.id = id;
	}
	
	@Override
	protected User run() throws Exception {
		return restTemplate.getForObject("http://client/users/{1}", User.class, id);
	}
	
	/**
	 * <h3>
	 *     重写请求缓存功能，开启请求缓存
	 *     该实例采用id作为缓存的key
	 * </h3>
	 * <p>
	 *     当不同的外部请求处理逻辑调用同一个依赖服务时，Hystrix会根据getCacheKey方法返回的值来区分是否是重复的请求，
	 *     如果他们的CacheKey相同，那么该依赖只会在第一个请求到达时被真实地调用一次，其他的请求将直接从缓存获取
	 *
	 *     优点：
	 *     		1、减少重复的请求数，降低依赖服务的并发度
	 *     		2、在同一用户请求的上下文中，相同依赖服务的返回数据始终保持一致
	 *     		3、请求缓存在run()和construct()执行生效之前，所以可以有效减少不必要的线程开销
	 * </p>
	 * @return
	 */
	@Override
	protected String getCacheKey() {
		return String.valueOf(id);
	}
	
	/**
	 * <h3>
	 *     	刷新缓存，根据id进行清理
	 * </h3>
	 * <p>
	 *     	实际使用中，在调用修改操作的请求后调用该方法清理之前对应的缓存
	 * </p>
	 * @param id
	 */
	public static void flashCache(Long id) {
		HystrixRequestCache.getInstance(GETTER_KEY, HystrixConcurrencyStrategyDefault.getInstance()).clear(String.valueOf(id));
	}
}
