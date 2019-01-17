package cn.markzang.springcloud;

import cn.markzang.springcloud.config.FilterConfiguration;
import com.netflix.zuul.FilterFileManager;
import com.netflix.zuul.FilterLoader;
import com.netflix.zuul.groovy.GroovyCompiler;
import com.netflix.zuul.groovy.GroovyFileFilter;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

/**
 * **** 该 java 文件的说明 ****
 *
 * @author BowenZang
 * @since 2019年01月17日
 */
@EnableZuulProxy
@EnableConfigurationProperties({FilterConfiguration.class})
@SpringCloudApplication
public class SpringCloudApiGatewayDynamicFilter {
	
	public static void main(String[] args) {
		new SpringApplicationBuilder(SpringCloudApiGatewayDynamicFilter.class).web(WebApplicationType.SERVLET).run(args);
	}
	
	@Bean
	public FilterLoader filterLoader(FilterConfiguration filterConfiguration) {
		FilterLoader filterLoader = FilterLoader.getInstance();
		filterLoader.setCompiler(new GroovyCompiler());
		
		try {
			FilterFileManager.setFilenameFilter(new GroovyFileFilter());
			FilterFileManager.init(filterConfiguration.getInterval(),
					filterConfiguration.getRoot() + "/pre",
					filterConfiguration.getRoot() + "/post");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return filterLoader;
	}
}
