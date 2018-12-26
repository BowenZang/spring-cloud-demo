package cn.markzang.springcloud.service;

/**
 * **** 该 java 文件的说明 ****
 *
 * @author BowenZang
 * @since 2018年12月19日
 */
public interface IConsumerService {
	
	String helloConsumer();
	
	String users(String id);
	
	String users();
	
}
