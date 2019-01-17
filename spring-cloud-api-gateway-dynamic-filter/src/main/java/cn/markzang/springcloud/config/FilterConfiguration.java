package cn.markzang.springcloud.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * **** 该 java 文件的说明 ****
 *
 * @author BowenZang
 * @since 2019年01月17日
 */
@ConfigurationProperties("zuul.filter")
public class FilterConfiguration {
	
	private String root;
	private Integer interval;
	
	public String getRoot() {
		return root;
	}
	
	public void setRoot(String root) {
		this.root = root;
	}
	
	public Integer getInterval() {
		return interval;
	}
	
	public void setInterval(Integer interval) {
		this.interval = interval;
	}
	
}
