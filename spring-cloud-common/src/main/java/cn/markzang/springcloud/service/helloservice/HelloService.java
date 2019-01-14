package cn.markzang.springcloud.service.helloservice;

import cn.markzang.springcloud.pojo.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <h3>
 *
 * </h3>
 *
 * @author BowenZang
 * @since 2019年01月14日
 */
@RequestMapping(value = "/refactor")
public interface HelloService {
	
	@GetMapping(value = "/hello4")
	String hello(@RequestParam("name") String name);
	
	@GetMapping(value = "/hello5")
	User hello(@RequestHeader("name") String name, @RequestHeader("age") Integer age);
	
	@PostMapping(value = "/hello6")
	String hello(@RequestBody User user);
}
