package cn.gzggzy.yyh;

import java.util.Date;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.gzggzy.yyh.service.MonthlyStatisticService;
import cn.gzggzy.yyh.service.PersonalCountOffService;
import cn.gzggzy.yyh.service.PersonalStatisticService;
import cn.gzggzy.yyh.service.WeeklyStatisticService;
import cn.gzggzy.yyh.service.YearlyStatisticService;
import cn.gzggzy.yyh.util.DateUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
public class XinjingApplicationTests {
	
	@Autowired
	private PersonalCountOffService personalCountOffService;
	
	@Autowired
	private WeeklyStatisticService weeklyStatisticService;
	
	@Autowired
	private MonthlyStatisticService monthlyStatisticService;
	
	@Autowired
	private YearlyStatisticService yearlyStatisticService;
	
	@Autowired
	private PersonalStatisticService personalStatisticService;
	

    
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
//		PersonalCountOff model = new PersonalCountOff();
//		Random random = new Random();
//		Calendar c = Calendar.getInstance();
//		int count = 0;
//		for (int i=0; i<13; i++) {
//			String pid = UUID.randomUUID().toString();
//			String uid = "19eb1b0f6d6b4fc4b0682e08c4025f3d";
//			int record_number = random.nextInt(20);
//			c.add(Calendar.DAY_OF_YEAR, -1);
//			Date date = c.getTime();
//			model.setPid(pid);
//			model.setUid(uid);
//			model.setRecord_date(date);
//			model.setRecord_number(record_number);
//			int result = personalCountOffService.saveOrUpdate(model);
//			if (result == 1) {
//				count ++;
//			}
//		}
//		System.out.println(count);
		
//		int result = personalCountOffService.weeklyStatisticByUID("19eb1b0f6d6b4fc4b0682e08c4025f3d", "2018-07-23", "2018-07-29");
//		System.out.println(result);
		
//		WeeklyStatistic ws = weeklyStatisticService.saveOrUpdate("19eb1b0f6d6b4fc4b0682e08c4025f3d", "2018-07-23", "2018-07-29");
//		System.out.println(ws);
		Date date = new Date();
		String dateStr = DateUtils.parseDateToStr(date, "yyyy-MM-dd");
		Map<String, Object> resultMap = personalStatisticService.personalTotal("09c18c679d2d41a0a29bb6f91df5010d", date, dateStr);
		
		System.out.println(((Map)resultMap.get("ws_map")).get("weekly_total"));
//		Map<String, Object> ws_map = weeklyStatisticService.selectWeekly("19eb1b0f6d6b4fc4b0682e08c4025f3d", null);
//		if (0 != ws_map.size()) {
//			System.out.println(ws_map.get("weekly_total"));
//			System.out.println(ws_map.get("ws")); 
//		} else {
//			System.out.println("没有相关记录！");
//		}
		
//		MonthlyStatistic ms = monthlyStatisticService.selectMonthly("19eb1b0f6d6b4fc4b0682e08c4025f3d", null);
//		if (null != ms) {
//			System.out.println(ms);
//		} else {
//			System.out.println("没有相关记录！");
//		}
//		YearlyStatistic ys = yearlyStatisticService.selectYearly("19eb1b0f6d6b4fc4b0682e08c4025f3d", null);
//		if (null != ys) {
//			System.out.println(ys);
//		} else {
//			System.out.println("没有相关记录！");
//		}
		
	}

}
