package cn.markzang.springcloud.config

import com.netflix.zuul.ZuulFilter
import com.netflix.zuul.context.RequestContext
import groovy.util.logging.Slf4j

import javax.servlet.http.HttpServletRequest

/**
 * **** 该 java 文件的说明 ****
 *
 * @author BowenZang* @since 2019年01月17日
 */
@Slf4j
class PreFilter extends ZuulFilter {
	
	@Override
	String filterType() {
		return "pre"
	}
	
	@Override
	int filterOrder() {
		return 1000
	}
	
	@Override
	boolean shouldFilter() {
		return true
	}
	
	@Override
	Object run() {
		HttpServletRequest request = RequestContext.getCurrentContext().getRequest()
		log.info("this is a pre filter: send {} request to {}", request.getMethod(), request.getRequestURL().toString())
		return null
	}
}
