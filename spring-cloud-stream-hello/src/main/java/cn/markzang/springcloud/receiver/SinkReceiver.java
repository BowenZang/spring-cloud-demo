package cn.markzang.springcloud.receiver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

/**
 * **** 该 java 文件的说明 ****
 *
 * @author BowenZang
 * @since 2019年01月22日
 */
@EnableBinding(Sink.class)
@Slf4j
public class SinkReceiver {

	@StreamListener(Sink.INPUT)
	public void receive(Object payload) {
		log.info("Received: " + payload);
	}

}
