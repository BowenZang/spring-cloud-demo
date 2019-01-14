package cn.markzang.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.turbine.stream.EnableTurbineStream;

/**
 * <h3>
 *     	turbine与消息代理结合
 * </h3>
 *
 * @author BowenZang
 * @since 2019年01月14日
 */
@EnableTurbineStream
@EnableDiscoveryClient
@SpringBootApplication
public class SpringCloudTurbineAmqp {
	
	public static void main(String[] args) {
		SpringApplication.run(SpringCloudTurbineAmqp.class, args);
	}
	
}
