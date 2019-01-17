package cn.markzang.springcloud;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * <h3>
 *     	配置中心从git获取配置
 * </h3>
 *
 * @author BowenZang
 * @since 2019年01月17日
 */
@EnableConfigServer
@SpringBootApplication
public class SpringCloudConfigServer {
	
	public static void main(String[] args) {
		new SpringApplicationBuilder(SpringCloudConfigServer.class).web(WebApplicationType.SERVLET).run(args);
	}
	
}
