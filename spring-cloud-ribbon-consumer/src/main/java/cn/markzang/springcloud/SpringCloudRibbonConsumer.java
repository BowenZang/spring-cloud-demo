package cn.markzang.springcloud;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * **** 该 java 文件的说明 ****
 *
 * @author BowenZang
 * @since 2018年12月17日
 */
//@SpringBootApplication
//@EnableDiscoveryClient
//@EnableCircuitBreaker
//上面三个注解可以用@SpringCloudApplication代替

@SpringCloudApplication
@EnableHystrix
public class SpringCloudRibbonConsumer {
	
	/**
	 * <h3>
	 *     加载bean，并开启客户端负载均衡
	 * </h3>
	 * @return
	 */
	@Bean
	@LoadBalanced
	RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	public static void main(String[] args) {
		new SpringApplicationBuilder(SpringCloudRibbonConsumer.class).web(WebApplicationType.SERVLET).run(args);
	}
	
}
