package cn.gzggzy.yyh.filter;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import cn.gzggzy.yyh.config.Configuration;
import cn.gzggzy.yyh.model.UserInfo;
import cn.gzggzy.yyh.util.CookieUtil;

@Component
public class LoginFilter {

	private static final Logger log = LoggerFactory.getLogger(LoginFilter.class);
	
	@Autowired
	private Configuration configuration;
	
	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	
//	public String[] checkLogin() {
//		String[] arr = new String[2];
//		String loginCookieVaule = CookieUtil.getCookie(configuration.getLoginCookieName());
//		log.info("loginCookieVaule: {}", loginCookieVaule);
//		if (null != loginCookieVaule) {
//			arr[0] = loginCookieVaule;
//			String key = "token::" + loginCookieVaule;
//			String uid = redisTemplate.opsForValue().get(key);
//			if (null != uid) {
//				//解决序列化造成的redis缓存字符串带双引号的问题
//				uid = uid.replace("\"", "");
//			}
//			log.info("uid: {}", uid);
//			arr[1] = uid;
//		}
//		return arr;
//	}
	
	public Map<String, Object> checkLogin() {
		String loginCookieVaule = CookieUtil.getCookie(configuration.getLoginCookieName());
		Map<String, Object> result = new HashMap<String, Object>();
		log.info("loginCookieVaule: {}", loginCookieVaule);
		if (null != loginCookieVaule) {
			String key = "user::" + loginCookieVaule;
			System.out.println(redisTemplate.opsForValue().get(key));
//			String userInfoJSON = redisTemplate.opsForValue().get(key);
			UserInfo userInfo = JSON.parseObject(redisTemplate.opsForValue().get(key), new TypeReference<UserInfo>() {});
			log.info("userInfo: {}", userInfo.toString());
			result.put("randomId", loginCookieVaule);
			result.put("userInfo", userInfo);
			redisTemplate.expire(key, 6000, TimeUnit.SECONDS);
		}
		return result;
	}
	
}
