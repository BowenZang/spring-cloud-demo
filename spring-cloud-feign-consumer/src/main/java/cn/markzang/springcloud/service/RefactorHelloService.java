package cn.markzang.springcloud.service;

import cn.markzang.springcloud.service.helloservice.HelloService;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * <h3>
 *     	refactorHelloService 整合的service
 * </h3>
 *
 * @author BowenZang
 * @since 2019年01月14日
 */
@FeignClient(value = "client")
public interface RefactorHelloService extends HelloService {
	
}
