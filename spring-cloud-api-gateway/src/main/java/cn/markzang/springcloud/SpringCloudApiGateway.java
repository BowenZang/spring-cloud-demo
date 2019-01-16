package cn.markzang.springcloud;

import cn.markzang.springcloud.filter.QueryParamPreFilter;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

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
	
	/**
	 * <h3>
	 *     	加载过滤器，载入bean，使过滤器生效
	 * </h3>
	 * @return
	 */
	@Bean
	public QueryParamPreFilter queryParamPreFilter() {
		return new QueryParamPreFilter();
	}
	
}
