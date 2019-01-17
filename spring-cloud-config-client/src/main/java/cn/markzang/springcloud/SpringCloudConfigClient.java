package cn.markzang.springcloud;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * <h3>
 *      客户端如何从配置中心加载配置
 * </h3>
 *
 * @author BowenZang
 * @since 2019年01月17日
 */
@SpringBootApplication
public class SpringCloudConfigClient {

	public static void main(String[] args) {
		new SpringApplicationBuilder(SpringCloudConfigClient.class).web(WebApplicationType.SERVLET).run(args);
	}

}
