package cn.gzggzy.yyh.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import cn.gzggzy.yyh.redis.CacheExpire;

@Service
public class RedisService {

    @Cacheable(value = "redis.service", unless = "#result == null or #result.empty")
    @CacheExpire(expire = 60)
    public Map<String, Object> get(String name) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", 123);
        map.put("name", name);
        return map;
    }
}
