package cn.markzang.springcloud.command;

import cn.markzang.springcloud.pojo.User;
import com.netflix.hystrix.HystrixObservableCommand;
import org.springframework.web.client.RestTemplate;
import rx.Observable;
import rx.Observable.OnSubscribe;
import rx.Subscriber;

/**
 * <h3>
 *     可以发射多次Observable
 * </h3>
 *
 * @author BowenZang
 * @since 2018年12月25日
 */
public class UserObsevableCommand extends HystrixObservableCommand<User> {
	
	private RestTemplate restTemplate;
	
	private Long id;
	
	public UserObsevableCommand(Setter setter, RestTemplate restTemplate, Long id) {
		super(setter);
		this.restTemplate = restTemplate;
		this.id = id;
	}
	
	@Override
	protected Observable<User> construct() {
		return Observable.create(new OnSubscribe<User>() {
			@Override
			public void call(Subscriber<? super User> observer) {
				try {
					if (!observer.isUnsubscribed()) {
						User user = restTemplate.getForObject("", User.class, id);
						observer.onNext(user);
						observer.onCompleted();
					}
				} catch (Exception e) {
					observer.onError(e);
				}
			}
		});
	}
	
	@Override
	protected Observable<User> resumeWithFallback() {
		return super.resumeWithFallback();
	}
	
}
