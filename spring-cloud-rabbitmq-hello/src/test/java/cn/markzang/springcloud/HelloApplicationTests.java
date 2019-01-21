package cn.markzang.springcloud;

import cn.markzang.springcloud.provider.Sender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * **** 该 java 文件的说明 ****
 *
 * @author BowenZang
 * @since 2019年01月19日
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration 较新版springboot放弃了该注解，使用@SpringBootTest就可以
@SpringBootTest(classes = SpringCloudRabbitmqHello.class)
public class HelloApplicationTests {
	
	@Autowired
	private Sender sender;
	
	@Test
	public void hello() {
		sender.send();
	}
}
