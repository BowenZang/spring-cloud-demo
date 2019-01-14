package cn.markzang.springcloud;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

/**
 * <h3>
 *     	turbine集群监控
 * </h3>
 *
 * @author BowenZang
 * @since 2019年01月14日
 */
@EnableTurbine
@EnableDiscoveryClient
@SpringBootApplication
public class SpringCloudTurbine {
	
	public static void main(String[] args) {
		new SpringApplicationBuilder(SpringCloudTurbine.class).web(WebApplicationType.SERVLET).run(args);
	}
	
}
