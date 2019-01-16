package cn.markzang.springcloud.filter;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.FORWARD_TO_KEY;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_DECORATION_FILTER_ORDER;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.SERVICE_ID_KEY;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import javax.servlet.http.HttpServletRequest;

/**
 * **** 该 java 文件的说明 ****
 *
 * @author BowenZang
 * @since 2019年01月16日
 */
public class QueryParamPreFilter extends ZuulFilter {
	
	/**
	 * <h3>
	 *     	过滤器的类型，它决定过滤器在请求的哪个生命周期中执行，这里定义为pre，代表会在请求被路由之前执行
	 * </h3>
	 *
	 * @return
	 */
	@Override
	public String filterType() {
		return PRE_TYPE;
	}
	
	/**
	 * <h3>
	 *     	过滤器的执行顺序。当请求在一个阶段中存在多个过滤器时，需要根据该方法返回的值来一次执行
	 * </h3>
	 *
	 * @return
	 */
	@Override
	public int filterOrder() {
		return PRE_DECORATION_FILTER_ORDER -1 ;
	}
	
	/**
	 * <h3>
	 *     	判断该过滤器是否需要被执行
	 * </h3>
	 *
	 * @return
	 */
	@Override
	public boolean shouldFilter() {
		RequestContext ctx = RequestContext.getCurrentContext();
		return !ctx.containsKey(FORWARD_TO_KEY) && !ctx.containsKey(SERVICE_ID_KEY);
	}
	
	/**
	 * <h3>
	 *     	过滤器的具体逻辑
	 * </h3>
	 * @return
	 */
	@Override
	public Object run() {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		if (request.getParameter("sample") != null) {
			// put the serviceId in 'RequestContext'
			ctx.put(SERVICE_ID_KEY, request.getParameter("foo"));
		}
		// 设置不进行路由
		ctx.setSendZuulResponse(false);
		// 设置返回code
		ctx.setResponseStatusCode(401);
		// 设置返回body
		ctx.setResponseBody("");
		return null;
	}
	
}
