package cn.markzang.springcloud;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * <h3>
 *     	API网关服务：Spring Cloud Zuul
 * </h3>
 *
 * @author BowenZang
 * @since 2019年01月15日
 */
@EnableZuulProxy
@SpringCloudApplication
public class SpringCloudApiGateway {
	
	public static void main(String[] args) {
		new SpringApplicationBuilder(SpringCloudApiGateway.class).web(WebApplicationType.SERVLET).run(args);
	}
	
}
