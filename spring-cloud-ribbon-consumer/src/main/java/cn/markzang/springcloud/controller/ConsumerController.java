package cn.markzang.springcloud.controller;

import cn.markzang.springcloud.command.UserCommand;
import cn.markzang.springcloud.pojo.User;
import cn.markzang.springcloud.service.IConsumerService;
import java.util.Random;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * **** 该 java 文件的说明 ****
 *
 * @author BowenZang
 * @since 2018年12月17日
 */
@RestController
@Slf4j
public class ConsumerController {
	
	@Autowired
	private IConsumerService consumerService;
	
	@GetMapping(value = "ribbon-consumer")
	public String helloConsumer() {
		return consumerService.helloConsumer();
	}
	
	@GetMapping(value = "/hello")
	public String hello() throws InterruptedException {
		// 超过2000毫秒，断路器就会生效，但是测试并不是这样 TODO
		Integer sleepTime = new Random().nextInt(30000);
		log.info("sleepTime: {}", sleepTime);
		Thread.sleep(sleepTime);
		return consumerService.helloConsumer();
	}
	
	@GetMapping(value = "/user")
	public String users() {
		return consumerService.users("0");
	}
}
