package cn.markzang.springcloud;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * **** 该 java 文件的说明 ****
 *
 * @author BowenZang
 * @since 2018年12月17日
 */
@SpringBootApplication
@EnableEurekaServer
public class SpringCloudEurekaServer {
	
	public static void main(String[] args) {
		new SpringApplicationBuilder(SpringCloudEurekaServer.class).web(WebApplicationType.SERVLET).run(args);
	}
	
}
