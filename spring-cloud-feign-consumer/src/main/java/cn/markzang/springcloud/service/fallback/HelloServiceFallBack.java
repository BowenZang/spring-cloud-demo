package cn.markzang.springcloud.service.fallback;

import cn.markzang.springcloud.pojo.User;
import cn.markzang.springcloud.service.HelloService;

/**
 * <h3>
 *     	HelloService的服务降级策略
 * </h3>
 *
 * @author BowenZang
 * @since 2019年01月14日
 */
public class HelloServiceFallBack implements HelloService {
	
	
	@Override
	public String hello() {
		return "error";
	}
	
	@Override
	public String hello(String name) {
		return "error";
	}
	
	@Override
	public User hello(String name, Integer age) {
		return new User("未知");
	}
	
	@Override
	public String hello(User user) {
		return "error";
	}
	
}
