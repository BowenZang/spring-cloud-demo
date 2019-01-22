package cn.markzang.springcloud;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * **** 该 java 文件的说明 ****
 *
 * @author BowenZang
 * @since 2019年01月22日
 */
@SpringBootApplication
public class SpringCloudStreamHello {
	
	public static void main(String[] args) {
		new SpringApplicationBuilder(SpringCloudStreamHello.class).web(WebApplicationType.SERVLET).run(args);
	}
	
}
