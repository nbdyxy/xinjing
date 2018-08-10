package cn.gzggzy.yyh;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.gzggzy.yyh.dao.PersonalCountOffDao;
import cn.gzggzy.yyh.model.PersonalCountOff;
import cn.gzggzy.yyh.service.PersonalCountOffService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class XinjingApplicationTests {
	
	@Autowired
	private PersonalCountOffService personalCountOffService;

    
//    @Test
//    public void setAndGet() {
//        redisTemplate.opsForValue().set("test:set", "testValue1");
//        Assert.assertEquals("testValue1", redisTemplate.opsForValue().get("test:set"));
//    }
    

//    @Test
//    public void get() {
//    	UserInfo userInfo = userInfoService.login("余颜宏", "76B9492A72C930E5");
//    	userInfo = userInfoService.login("余颜宏", "76B9492A72C930E5");
//    	userInfo = userInfoService.login("余颜宏", "76B9492A72C930E5");
//    	System.out.println(userInfo.getPassword());
//    	Map<String, Object> map = redisService.get("余颜宏");
//    	map = redisService.get("余颜宏");
//    	map = redisService.get("余颜宏");
//    	map = redisService.get("余颜宏");
    	
//    }
	
	@Test
	public void insert() {
		PersonalCountOff model = new PersonalCountOff();
		Random random = new Random();
		Calendar c = Calendar.getInstance();
		int count = 0;
		for (int i=0; i<20; i++) {
			String pid = UUID.randomUUID().toString();
			String uid = "09c18c679d2d41a0a29bb6f91df5010d";
			int record_number = random.nextInt(20);
			c.add(Calendar.DAY_OF_YEAR, -1);
			Date date = c.getTime();
			model.setPid(pid);
			model.setUid(uid);
			model.setRecord_date(date);
			model.setRecord_number(record_number);
//			int result = personalCountOffService.saveOrUpdate(model);
//			if (result == 1) {
//				count ++;
//			}
		}
		System.out.println(count);
	}

}
