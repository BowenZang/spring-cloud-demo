package cn.markzang.springcloud.service;

import cn.markzang.springcloud.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <h3>
 *      HelloService 接口
 *      用Feign来统一管理服务
 *      FeignClient配置提供服务的服务名
 * </h3>
 *
 * @author BowenZang
 * @since 2019年01月14日
 */
@FeignClient(value = "client1")
public interface HelloService {
	
	@GetMapping("/")
	String hello();
	
	/**
	 * <h3>
	 *     	这里注意使用@RequestParam和RequestHeader必须指定参数名，SpringMVC会将参数名作为默认值
	 *     	但是feign不会，所以必须指定
	 * </h3>
	 * @param name
	 * @return
	 */
	@GetMapping("/hello1")
	String hello(@RequestParam("name") String name);
	
	@GetMapping("/hello2")
	User hello(@RequestHeader("name") String name, @RequestHeader("age") Integer age);
	
	@PostMapping("/hello3")
	String hello(@RequestBody User user);
}
