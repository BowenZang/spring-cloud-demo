package cn.markzang.springcloud.command;

import cn.markzang.springcloud.pojo.User;
import com.netflix.hystrix.HystrixCommand;
import org.springframework.web.client.RestTemplate;

/**
 * 修改用户，POST方式
 *
 * @author BowenZang
 * @since 2018年12月29日
 */
public class UserPostCommand extends HystrixCommand<User> {
	
	private RestTemplate restTemplate;
	
	private User user;
	
	public UserPostCommand(Setter setter, RestTemplate restTemplate, User user) {
		super(setter);
		this.restTemplate = restTemplate;
		this.user = user;
	}
	
	@Override
	public User run() throws Exception {
		// 写入操作
		User resultUser = restTemplate.postForObject("http://client/users", user, User.class);
		
		// 刷新缓存，删除缓存中失效的User
		UserGetCommand.flashCache(user.getId());
		return resultUser;
	}
	
}
