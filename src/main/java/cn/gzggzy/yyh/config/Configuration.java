package cn.gzggzy.yyh.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="config")
@PropertySource("classpath:/Configure.properties")
public class Configuration {

	private String loginCookieName;
	private String key;
	
	public String getLoginCookieName() {
		return loginCookieName;
	}

	public void setLoginCookieName(String loginCookieName) {
		this.loginCookieName = loginCookieName;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	
}
