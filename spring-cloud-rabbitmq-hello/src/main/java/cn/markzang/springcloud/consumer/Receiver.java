package cn.markzang.springcloud.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * <h3>
 *     	消息消费者
 * </h3>
 *
 * @author BowenZang
 * @since 2019年01月19日
 */
@Component
@RabbitListener(queues = "hello")
public class Receiver {

	@RabbitHandler
	public void process(String hello) {
		System.out.println("Receiver : " + hello);
	}

}
