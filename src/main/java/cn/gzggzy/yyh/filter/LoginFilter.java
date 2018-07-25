package cn.gzggzy.yyh.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import cn.gzggzy.yyh.config.Configuration;
import cn.gzggzy.yyh.util.CookieUtil;

@Component
public class LoginFilter {

	private static final Logger log = LoggerFactory.getLogger(LoginFilter.class);
	
	@Autowired
	private Configuration configuration;
	
	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	
	public String checkLogin() {
		String loginCookieVaule = CookieUtil.getCookie(configuration.getLoginCookieName());
		log.info("loginCookieVaule: {}", loginCookieVaule);
		if (null != loginCookieVaule) {
			String key = "token::" + loginCookieVaule;
			String uid = redisTemplate.opsForValue().get(key);
			log.info("uid: {}", uid);
			return uid;
		}
		return null;
	}
	
}
