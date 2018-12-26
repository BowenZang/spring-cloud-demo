package cn.markzang.springcloud.command;

import cn.markzang.springcloud.pojo.User;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
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
	
	public UserCommand() {
		super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("GroupName")).andCommandKey(
				HystrixCommandKey.Factory.asKey("CommandName")));
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
	
	@Override
	protected User getFallback() {
		return new User();
	}
	
}
