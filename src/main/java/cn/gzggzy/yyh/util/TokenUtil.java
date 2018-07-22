package cn.gzggzy.yyh.util;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import cn.gzggzy.yyh.config.Configuration;
import cn.gzggzy.yyh.redis.CacheExpire;

@Component
public class TokenUtil {
	
	@Autowired
	private Configuration configuration;
	
	@Cacheable(value = "token", key = "#loginid")
	@CacheExpire(expire = 6000)
	public String createToken(String loginid) {
		String ticketKey = UUID.randomUUID().toString().replace("-", "");
		String encodedticketKey = DESUtils.encrypt(ticketKey, configuration.getKey());
		return encodedticketKey;
	}
}
