package cn.markzang.springcloud;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * <h3>
 *     	Hystrix 仪表盘
 * </h3>
 *
 * @author BowenZang
 * @since 2019年01月10日
 */
@SpringBootApplication
@EnableHystrixDashboard
public class SpringCloudHystrixDashboard {

	public static void main(String[] args) {
		new SpringApplicationBuilder(SpringCloudHystrixDashboard.class).web(WebApplicationType.SERVLET).run(args);
	}

}
