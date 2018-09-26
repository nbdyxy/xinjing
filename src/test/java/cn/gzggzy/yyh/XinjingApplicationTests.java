package cn.gzggzy.yyh;

import java.util.Iterator;
import java.util.Set;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.test.context.junit4.SpringRunner;

import cn.gzggzy.yyh.service.MonthlyStatisticService;
import cn.gzggzy.yyh.service.PersonalCountOffService;
import cn.gzggzy.yyh.service.PersonalStatisticService;
import cn.gzggzy.yyh.service.RankService;
import cn.gzggzy.yyh.service.UserInfoService;
import cn.gzggzy.yyh.service.WeeklyStatisticService;
import cn.gzggzy.yyh.service.YearlyStatisticService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class XinjingApplicationTests {
	
	@Autowired
	private UserInfoService userInfoService;
	
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
	
	@Autowired
	private RankService rankService;
	
	@Autowired
	private CacheManager cacheManager;
    
	@Autowired
	private RedisTemplate<String, Object> template;
	
	@Resource(name="redisTemplate")
	private ListOperations<String, String> listOps;
	
	@Resource(name="redisTemplate")
	private ZSetOperations<String, String> zsetOps;
	
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
		
//		System.out.println(userInfoService.selectUserNumber(false));
		
		int result = personalCountOffService.selectOneDayTotal("2018-07-25");
		System.out.println(result);
		
//		result = weeklyStatisticService.selectOneWeekTotal(2018, 9, 38, 3);
//		System.out.println(result);
		
		result = monthlyStatisticService.selectOneMonthTotal(2018, 9);
		System.out.println(result);
		
		result = yearlyStatisticService.selectYearTotal(2018);
		System.out.println(result);
		
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
//		Date date = new Date();
//		Date beginDate = DateUtils.getFirstDayOfWeek(date);
//		String beginDateStr = DateUtils.parseDateToStr(beginDate, "yyyy-MM-dd");
//		String endDateStr = DateUtils.parseDateToStr(date, "yyyy-MM-dd");
//		System.out.println("beginDateStr: " + beginDateStr + "======" + "endDateStr: " + endDateStr);
//		int result = personalCountOffService.weeklyStatisticByUID("09c18c679d2d41a0a29bb6f91df5010d", beginDateStr, endDateStr);
//		System.out.println(result);
//		Map<String, Object> resultMap = personalStatisticService.personalTotal("09c18c679d2d41a0a29bb6f91df5010d", date, dateStr);
		
//		System.out.println(((Map)resultMap.get("ws_map")).get("weekly_total"));
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
//		listOps.leftPush("listOps", "test");
//		zsetOps.add("weekly", "user1", 30);
//		zsetOps.add("weekly", "user2", 20);
//		zsetOps.add("weekly", "user3", 10);
//		template.expire("weekly", 100, TimeUnit.MINUTES);
//		System.out.println(template.getExpire("weekly", TimeUnit.MINUTES));
//		Set<String> result = zsetOps.reverseRange("weekly", 0, 2);
//		System.out.println(zsetOps.reverseRank("weekly", "user1"));
//		Iterator<String> it = result.iterator();
//		while(it.hasNext()) {
//			System.out.println(it.next());
//		}
//		zsetOps.add("weekly", "user1", 5);
//		template.expire("weekly", 10, TimeUnit.MINUTES);
//		System.out.println(template.getExpire("weekly", TimeUnit.MINUTES));
//		System.out.println(zsetOps.reverseRank("daily180909", "09c18c679d2d41a0a29bb6f91df5010d"));
//		Set<String> result2 = zsetOps.reverseRange("weekly", 0, 2);
//		Iterator<String> it2 = result2.iterator();
//		while(it2.hasNext()) {
//			System.out.println(it2.next());
//		}
//		zsetOps.add("weekly", "user1", 30);
//		zsetOps.reverseRank("weekly", "user1");
//		System.out.println(template.getExpire("user::1euyE038", TimeUnit.MINUTES));
//		Calendar c = Calendar.getInstance();
//		String type = "daily";
//		rankService.rankAdd("user1", c.getTime(), type, 21);
//		rankService.rankAdd("user2", c.getTime(), type, 12);
//		rankService.rankAdd("user3", c.getTime(), type, 23);
//		rankService.rankAdd("user4", c.getTime(), type, 25);
//		rankService.rankAdd("user5", c.getTime(), type, 26);
//		String dateStr = DateUtils.parseDateToStr(c.getTime(), "yyMMdd");
//		String key = type + dateStr;
//		System.out.println(template.getExpire(key, TimeUnit.HOURS));
//		System.out.println(template.getExpire(key, TimeUnit.MINUTES));
		
//		long size = zsetOps.size("monthly180930");
//		System.out.println("size: " + size);
//		long count = zsetOps.count("monthly180930", 0, size);
//		System.out.println("count: " + count);
//		Set<String> mySet  = zsetOps.range("monthly180930", 0, size);
//		Iterator<String> it = mySet.iterator();
//		while(it.hasNext()) {
//			System.out.println(it.next());
//		}
		
	}

}
