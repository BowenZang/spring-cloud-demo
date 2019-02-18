package cn.markzang.springcloud.receiver;

import cn.markzang.springcloud.pojo.User;
import cn.markzang.springcloud.sender.SinkSender;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.annotation.Transformer;

/**
 * **** 该 java 文件的说明 ****
 *
 * @author BowenZang
 * @since 2019年01月22日
 */
//@EnableBinding({Sink.class, SinkSender.class})
@EnableBinding({Sink.class})
@Slf4j
public class SinkReceiver {

//	@StreamListener(Sink.INPUT)
	@ServiceActivator(inputChannel = Sink.INPUT)
	public void receive(Object payload) {
		log.info("Received: " + payload);
	}
	
	/**
	 * <h3>
	 * 		用@StreamListener注解就无需用transformer将信息手动转换成实体类
	 * 		注；需要在配置文件增加配置
	 * 			spring.cloud.stream.bindings.input.content-type = application/json
	 * </h3>
	 *
	 * @param user
	 */
	@ServiceActivator(inputChannel = Sink.INPUT)
//	@StreamListener(Sink.INPUT)
	public void receive(User user) {
		log.info("Received:" + user);
	}
	
	/**
	 * <h3>
	 *     	从消息中解析数据封装成User实体类
	 * </h3>
	 *
	 * @param message
	 * @return
	 * @throws IOException
	 */
	@Transformer(inputChannel = Sink.INPUT, outputChannel = Sink.INPUT)
	public User transform(String message) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		User user = objectMapper.readValue(message, User.class);
		return user;
	}

}
