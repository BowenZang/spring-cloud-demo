package cn.markzang.springcloud.feedback;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.handler.annotation.SendTo;

/**
 * <h3>
 *     	消息反馈
 * </h3>
 *
 * @author BowenZang
 * @since 2019年01月23日
 */
@EnableBinding(value = {Processor.class})
@Slf4j
public class App1 {

//	@StreamListener(Processor.INPUT)
//	@SendTo(Processor.OUTPUT)
//	public Object receiveFromInput(Object payload) {
//		log.info("Received: " + payload);
//		return "From Input Channer Return - " + payload;
//	}


}
