package cn.markzang.springcloud.sender;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * **** 该 java 文件的说明 ****
 *
 * @author BowenZang
 * @since 2019年01月23日
 */
public interface MySource {
	
	@Output("Output-1")
	MessageChannel output1();
	
	@Output("Output-2")
	MessageChannel output2();
}
