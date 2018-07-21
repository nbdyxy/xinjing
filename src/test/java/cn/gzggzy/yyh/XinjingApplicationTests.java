package cn.gzggzy.yyh;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.gzggzy.yyh.model.UserInfo;
import cn.gzggzy.yyh.service.UserInfoService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class XinjingApplicationTests {
	
	private static final Logger log = LoggerFactory.getLogger(XinjingApplicationTests.class);

    
    @Autowired
    private UserInfoService userInfoService;
    
//    @Test
//    public void setAndGet() {
//        redisTemplate.opsForValue().set("test:set", "testValue1");
//        Assert.assertEquals("testValue1", redisTemplate.opsForValue().get("test:set"));
//    }
    

    @Test
    public void get() {
    	UserInfo userInfo = userInfoService.login("余颜宏", "120537");
    	userInfo = userInfoService.login("余颜宏", "120537");
    	userInfo = userInfoService.login("余颜宏", "120537");
    	System.out.println(userInfo.getPassword());
//    	Map<String, Object> map = redisService.get("余颜宏");
//    	map = redisService.get("余颜宏");
//    	map = redisService.get("余颜宏");
//    	map = redisService.get("余颜宏");
    	
    }

}
