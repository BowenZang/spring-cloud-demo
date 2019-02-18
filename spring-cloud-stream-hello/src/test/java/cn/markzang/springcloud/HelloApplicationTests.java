package cn.markzang.springcloud;

import cn.markzang.springcloud.sender.SinkSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * <h3>
 *     	启动报错，后续查看问题(sinkSender) TODO
 * </h3>
 *
 * @author BowenZang
 * @since 2019年01月23日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringCloudStreamHello.class)
@WebAppConfiguration
public class HelloApplicationTests {
	
	@Autowired
	private SinkSender sinkSender;
	
	@Autowired
	private MessageChannel input;
	
	@Test
	public void contextLoads() {
//		sinkSender.output().send(MessageBuilder.withPayload("From SinkSender").build());
		input.send(MessageBuilder.withPayload("From MessageChannel").build());
	}
	
}
