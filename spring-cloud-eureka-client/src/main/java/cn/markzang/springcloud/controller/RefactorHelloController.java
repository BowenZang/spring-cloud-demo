package cn.markzang.springcloud.controller;

import cn.markzang.springcloud.pojo.User;
import cn.markzang.springcloud.service.helloservice.HelloService;
import org.springframework.web.bind.annotation.RestController;

/**
 * **** 该 java 文件的说明 ****
 *
 * @author BowenZang
 * @since 2019年01月14日
 */
@RestController
public class RefactorHelloController implements HelloService {
	
	@Override
	public String hello(String name) {
		return "Hello!" + name + "!";
	}
	
	@Override
	public User hello(String name, Integer age) {
		return new User(name, age);
	}
	
	@Override
	public String hello(User user) {
		return "Hello!" + user.getName() + ", " + user.getAge();
	}
	
}
