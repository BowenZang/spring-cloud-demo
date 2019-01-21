package cn.markzang.springcloud.configuration;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * **** 该 java 文件的说明 ****
 *
 * @author BowenZang
 * @since 2019年01月19日
 */
@Configuration
public class RabbitConfig {

	@Bean
	public Queue helloQueue() {
		return new Queue("hello");
	}

}
