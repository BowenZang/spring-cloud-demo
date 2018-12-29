package cn.markzang.springcloud.command;

import cn.markzang.springcloud.pojo.User;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixRequestCache;
import com.netflix.hystrix.HystrixThreadPoolKey;
import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategyDefault;
import org.springframework.web.client.RestTemplate;

/**
 * <h3>
 *     用户请求命令，HystrixCommand采用命令设计模式
 *     只能发射一次Observable
 * </h3>
 *
 * @author BowenZang
 * @since 2018年12月25日
 */
public class UserCommand extends HystrixCommand<User> {
	
	private RestTemplate restTemplate;
	
	private Long id;
	
	/**
	 * 分组，命令名称，线程池划分
	 * 默认（线程池划分是通过命令分组来实现的）
	 * 通常情况下，尽量通过HystrixThreadPoolKey来指定线程池的划分，而不是通过默认方式实现划分，因为多个不同的命令可能从业务逻辑上
	 * 来看属于同一个组，但是往往从实现本身上需要跟其他命令进行隔离
	 */
	public UserCommand() {
		super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("GroupName")).andCommandKey(
				HystrixCommandKey.Factory.asKey("CommandName")).andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("ThreadPoolKey")));
	}
	
	public UserCommand(Setter setter, RestTemplate restTemplate, Long id) {
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
	 *     重写服务降级代码
	 * </h3>
	 * @return
	 */
	@Override
	protected User getFallback() {
		return new User();
	}
	
}
