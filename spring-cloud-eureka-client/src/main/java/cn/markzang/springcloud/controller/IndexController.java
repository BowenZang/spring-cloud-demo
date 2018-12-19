package cn.markzang.springcloud.controller;

import org.springframework.web.bind.annotation.GetMapping;
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
	
}
