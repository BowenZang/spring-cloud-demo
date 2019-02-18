package cn.markzang.springcloud.feedback;

import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.core.MessageSource;
import org.springframework.messaging.support.GenericMessage;

/**
 * **** 该 java 文件的说明 ****
 *
 * @author BowenZang
 * @since 2019年01月23日
 */
@EnableBinding(value = {Processor.class})
@Slf4j
public class App2 {

	@Bean
	@InboundChannelAdapter(value = Processor.OUTPUT, poller = @Poller(fixedDelay = "2000"))
	public MessageSource<Date> timerMessageSource() {
		return () -> new GenericMessage<>(new Date());
	}
	
	@StreamListener(Processor.INPUT)
	public void receiveFromOutput(Object payload) {
		log.info("Seceived: " + payload);
	}

}
