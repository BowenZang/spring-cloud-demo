package cn.markzang.springcloud.controller;

import cn.markzang.springcloud.pojo.User;
import cn.markzang.springcloud.service.HelloService;
import cn.markzang.springcloud.service.RefactorHelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <h3>
 *     	consumerController
 * </h3>
 *
 * @author BowenZang
 * @since 2019年01月14日
 */
@RestController
public class ConsumerController {

	@Autowired
	private HelloService helloService;

	@GetMapping(value = "/feign-consumer")
	public String helloConsumer() {
		return helloService.hello();
	}
	
	@GetMapping(value = "/feign-consumer2")
	public String helloConsumer2() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(helloService.hello()).append("\n");
		stringBuilder.append(helloService.hello("DIDI")).append("\n");
		stringBuilder.append(helloService.hello("DIDI", 18)).append("\n");
		stringBuilder.append(helloService.hello(new User("DIDI", 20))).append("\n");
		return stringBuilder.toString();
	}
	
	@Autowired
	private RefactorHelloService refactorHelloService;
	
	@GetMapping(value = "/feign-consumer3")
	public String helloConsumer3() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(refactorHelloService.hello("MIMI")).append("\n");
		stringBuilder.append(refactorHelloService.hello("MIMI", 18)).append("\n");
		stringBuilder.append(refactorHelloService.hello(new User("MIMI", 20))).append("\n");
		return stringBuilder.toString();
	}
}
