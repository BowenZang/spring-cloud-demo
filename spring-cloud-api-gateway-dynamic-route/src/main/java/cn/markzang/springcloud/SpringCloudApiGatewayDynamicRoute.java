package cn.markzang.springcloud;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.context.annotation.Bean;

/**
 * **** 该 java 文件的说明 ****
 *
 * @author BowenZang
 * @since 2019年01月17日
 */
@EnableZuulProxy
@SpringCloudApplication
public class SpringCloudApiGatewayDynamicRoute {
	
	public static void main(String[] args) {
		new SpringApplicationBuilder(SpringCloudApiGatewayDynamicRoute.class).web(WebApplicationType.SERVLET).run(args);
	}
	
	@Bean
	@RefreshScope
	@ConfigurationProperties("zuul")
	public ZuulProperties zuulProperties() {
		return new ZuulProperties();
	}
}
