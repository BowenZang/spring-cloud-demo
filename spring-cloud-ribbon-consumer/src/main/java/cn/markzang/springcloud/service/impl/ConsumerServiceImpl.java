package cn.markzang.springcloud.service.impl;

import cn.markzang.springcloud.service.IConsumerService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
	
	@Override
	public String helloForBack() {
		return "error";
	}
	
}
