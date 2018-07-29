package cn.gzggzy.yyh.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import cn.gzggzy.yyh.config.Configuration;
import cn.gzggzy.yyh.util.CookieUtil;

@Component
public class LoginFilter {

	private static final Logger log = LoggerFactory.getLogger(LoginFilter.class);
	
	@Autowired
	private Configuration configuration;
	
	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	
	public String[] checkLogin() {
		String[] arr = new String[2];
		String loginCookieVaule = CookieUtil.getCookie(configuration.getLoginCookieName());
		log.info("loginCookieVaule: {}", loginCookieVaule);
		if (null != loginCookieVaule) {
			arr[0] = loginCookieVaule;
			String key = "token::" + loginCookieVaule;
			String uid = redisTemplate.opsForValue().get(key);
			if (null != uid) {
				//解决序列化造成的redis缓存字符串带双引号的问题
				uid = uid.replace("\"", "");
			}
			log.info("uid: {}", uid);
			arr[1] = uid;
		}
		return arr;
	}
	
}
