package cn.markzang.springcloud.command;

import cn.markzang.springcloud.pojo.User;
import cn.markzang.springcloud.service.impl.ConsumerServiceImpl;
import com.netflix.hystrix.HystrixCollapser;
import com.netflix.hystrix.HystrixCollapserKey;
import com.netflix.hystrix.HystrixCollapserProperties;
import com.netflix.hystrix.HystrixCommand;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <h3>
 *     	实现请求合并
 * </h3>
 *
 * @author BowenZang
 * @since 2018年12月29日
 */
public class UserCollapseCommand extends HystrixCollapser<List<User>, User, Long> {
	
	private ConsumerServiceImpl consumerService;
	private Long userId;
	
	/**
	 * <h3>
	 *  	请求合并设置了时间延迟属性100毫秒
	 * </h3>
	 *
	 * @param consumerService
	 * @param userId
	 */
	public UserCollapseCommand(ConsumerServiceImpl consumerService, Long userId) {
		super(Setter.withCollapserKey(HystrixCollapserKey.Factory.asKey("userCollapseCommand")).andCollapserPropertiesDefaults(
				HystrixCollapserProperties.Setter().withTimerDelayInMilliseconds(100)));
		this.consumerService = consumerService;
		this.userId = userId;
	}
	
	@Override
	public Long getRequestArgument() {
		return userId;
	}
	
	@Override
	protected HystrixCommand<List<User>> createCommand(Collection<CollapsedRequest<User, Long>> collapsedRequests) {
		List<Long> userIds = new ArrayList<>(collapsedRequests.size());
		userIds.addAll(collapsedRequests.stream().map(CollapsedRequest::getArgument).collect(Collectors.toList()));
		return new UserBatchCommand(consumerService, userIds);
	}
	
	@Override
	protected void mapResponseToRequests(List<User> users, Collection<CollapsedRequest<User, Long>> collapsedRequests) {
		int count = 0;
		for (CollapsedRequest<User, Long> collapsedRequest : collapsedRequests) {
			User user = users.get(count++);
			collapsedRequest.setResponse(user);
		}
	}
	
}
