package cn.gzggzy.yyh.util;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import cn.gzggzy.yyh.redis.CacheExpire;

@Component
public class TokenUtil {
	
	
	@Cacheable(value = "token", key = "#loginid", unless="#result == null")
	@CacheExpire(expire = 6000)
	public String createToken(String loginid, String uid) {
		return uid;
	}
}
