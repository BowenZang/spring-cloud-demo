package cn.markzang.springcloud.sender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;

/**
 * **** 该 java 文件的说明 ****
 *
 * @author BowenZang
 * @since 2019年01月23日
 */
@Component
public class OutputSender {
	
	@Autowired
	@Qualifier("Output-1")
	private MessageChannel output;
	
}
