package cn.markzang.springcloud.command;

import static com.netflix.hystrix.HystrixCommandGroupKey.Factory.asKey;

import cn.markzang.springcloud.pojo.User;
import cn.markzang.springcloud.service.impl.ConsumerServiceImpl;
import com.netflix.hystrix.HystrixCommand;
import java.util.List;

/**
 * <h3>
 *      合并请求
 * </h3>
 *
 * @author BowenZang
 * @since 2018年12月29日
 */
public class UserBatchCommand extends HystrixCommand<List<User>> {
	ConsumerServiceImpl consumerService;
	List<Long> userIds;
	
	public UserBatchCommand(ConsumerServiceImpl consumerService, List<Long> userIds) {
		super(Setter.withGroupKey(asKey("consumerService")));
		consumerService = consumerService;
		userIds = userIds;
	}
	
	@Override
	protected List<User> run() throws Exception {
		return consumerService.findAll(userIds);
	}
	
}
