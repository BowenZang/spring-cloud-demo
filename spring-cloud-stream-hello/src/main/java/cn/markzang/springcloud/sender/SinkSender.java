package cn.markzang.springcloud.sender;

import java.util.Date;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.core.MessageSource;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.GenericMessage;

/**
 * **** 该 java 文件的说明 ****
 *
 * @author BowenZang
 * @since 2019年01月23日
 */
@EnableBinding(value = {SinkSender.SinkOutput.class})
public class SinkSender {
	
//	@Output(Sink.INPUT)
//	MessageChannel output();
	
	@Bean
	@InboundChannelAdapter(value = SinkOutput.OUTPUT, poller = @Poller(fixedDelay = "2000"))
	public MessageSource<String> timerMessageSource() {
		//		return new MessageSource<Date>() {
		//			@Override
		//			public Message<Date> receive() {
		//				return new GenericMessage<>(new Date());
		//			}
		//		};
//		return () -> new GenericMessage<>(new Date());
		return () -> new GenericMessage<>("{\"name\":\"didi\", \"age\":30}");
		
	}
	
	
	public interface SinkOutput {
		String OUTPUT = "input";
		
		@Output(OUTPUT)
		MessageChannel output();
	}
}
