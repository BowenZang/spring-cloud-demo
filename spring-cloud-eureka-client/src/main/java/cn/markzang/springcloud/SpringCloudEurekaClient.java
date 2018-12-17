package cn.markzang.springcloud;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * **** 该 java 文件的说明 ****
 *
 * @author BowenZang
 * @since 2018年12月17日
 */
@SpringBootApplication
@EnableDiscoveryClient
public class SpringCloudEurekaClient {
	
	public static void main(String[] args) {
		new SpringApplicationBuilder(SpringCloudEurekaClient.class).web(WebApplicationType.SERVLET).run(args);
	}
	
}
