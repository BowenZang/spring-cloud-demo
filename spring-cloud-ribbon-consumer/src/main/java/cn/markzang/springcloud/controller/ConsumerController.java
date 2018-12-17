package cn.markzang.springcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * **** 该 java 文件的说明 ****
 *
 * @author BowenZang
 * @since 2018年12月17日
 */
@RestController
public class ConsumerController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@GetMapping(value = "ribbon-consumer")
	public String helloConsumer() {
		return restTemplate.getForEntity("http://client/", String.class).getBody();
	}

}
