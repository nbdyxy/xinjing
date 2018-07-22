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
	
	public String checkLogin(BindingResult bindingResult) {
		String loginCookieVaule = CookieUtil.getCookie(configuration.getLoginCookieName());
		log.info("loginCookieVaule: {}", loginCookieVaule);
		if (null != loginCookieVaule) {
			long ttl = redisTemplate.getExpire("token::" + loginCookieVaule);
			log.info("TTL: {}", ttl);
			if (ttl > 0) {
				return "redirect:gongxiu_personally";
			} else {
				ObjectError error = new ObjectError("login", "登录信息过期，请重新登录");
				bindingResult.addError(error);
				return "gongxiu";
			}
		}
		return "gongxiu";
	}
	
}
