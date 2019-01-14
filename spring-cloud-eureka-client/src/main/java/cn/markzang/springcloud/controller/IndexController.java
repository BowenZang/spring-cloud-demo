package cn.markzang.springcloud.controller;

import cn.markzang.springcloud.pojo.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * **** 该 java 文件的说明 ****
 *
 * @author BowenZang
 * @since 2018年12月17日
 */
@RestController
public class IndexController {
	
	@GetMapping("/")
	public String toIndex() {
		return "Hello World!";
	}
	
	@GetMapping("/hello1")
	public String hello(@RequestParam String name) {
		return "Hello ! " + name + " !";
	}
	
	@GetMapping("/hello2")
	public User hello(@RequestHeader String name, @RequestHeader Integer age) {
		return new User(name, age);
	}
	
	@PostMapping("/hello3")
	public String hello(@RequestBody User user) {
		return "Hello !" + user.getName() + ", " + user.getAge();
	}
	
}
