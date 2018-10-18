package cn.gzggzy.yyh;

import java.text.ParseException;

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

import cn.gzggzy.yyh.service.DynamicsService;
import cn.gzggzy.yyh.service.MonthlyStatisticService;
import cn.gzggzy.yyh.service.PersonalCountOffService;
import cn.gzggzy.yyh.service.PersonalStatisticService;
import cn.gzggzy.yyh.service.PrivateActivityService;
import cn.gzggzy.yyh.service.PublicActivityService;
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
	private DynamicsService dynamicsService;
	
	@Autowired
	private PublicActivityService publicActivityService;
	
	@Autowired
	private PrivateActivityService privateActivityService;
	
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
	public void insert() throws ParseException {
		
		int result = personalCountOffService.selectPublicActivityCountNumber("2e955926-c28f-429e-a2b5-97e1b44db7f3");
		System.out.println("公共活动报数总量: " + result);
		
		result = personalCountOffService.selectPrivateActivityCountNumber("d17273df-2457-4c30-9194-6ddcf6b10b2d");
		System.out.println("私有活动报数总量: " + result);
		
		result = personalCountOffService.selectActivityJoinNumber("");
		System.out.println("所有活动参与人数： " + result);
		
		result = personalCountOffService.selectPublicActivityCountNumber("2e955926-c28f-429e-a2b5-97e1b44db7f3");
		System.out.println("某公共活动参与人数： " + result);
		
		
//		int i = 1;
//		int count = 0;
//		while(i++ <101) {
//			PrivateActivity pa = new PrivateActivity();
//			pa.setPrivate_activity_id(UUID.randomUUID().toString());
//			pa.setPrivate_activity_creator_id("09c18c679d2d41a0a29bb6f91df5010d");
//			pa.setPrivate_activity_name("第" + i + "次《心经》百万共修活动");
//			pa.setPrivate_activity_begin_time(DateUtils.parseStrToDate("2018-01-01", "yyyy-MM-dd"));
//			pa.setPrivate_activity_end_time(DateUtils.parseStrToDate("2018-12-31", "yyyy-MM-dd"));
//			pa.setPrivate_activity_target(1000000);
//			pa.setPrivate_activity_create_time(DateUtils.parseStrToDate("2017-11-20 08:32:15", "yyyy-MM-dd HH:mm:ss"));
//			pa.setPrivate_activity_enable(i%2);
//			pa.setPrivate_activity_update_time(DateUtils.parseStrToDate("2017-11-20 08:32:15", "yyyy-MM-dd HH:mm:ss"));
//			int result = privateActivityService.insertPrivateActivity(pa);
//			count += result;
//		}
//		System.out.println(count);
		
		
//		List<PublicActivity> pas = publicActivityService.selectActivityEnable(1, 10, "public_activity_name desc", true);
//		System.out.println(pas.get(0).getPublic_activity_name());
//		System.out.println(((Page) pas).getTotal());
//		
//		pas = publicActivityService.selectActivityEnable(1, 10, "public_activity_name asc", true);
//		System.out.println(pas.get(0).getPublic_activity_name());
//		System.out.println(((Page) pas).getTotal());
//		
//		pas = publicActivityService.selectActivityAll(1, 10, "public_activity_name desc", true);
//		System.out.println(pas.get(0).getPublic_activity_name());
//		System.out.println(((Page) pas).getTotal());
//		
//		pas = publicActivityService.selectActivityAll(1, 10, "public_activity_name asc", true);
//		System.out.println(pas.get(0).getPublic_activity_name());
//		System.out.println(((Page) pas).getTotal());
		
//		List<PrivateActivity> pas = privateActivityService.selectActivityEnable(1, 10, "private_activity_name desc", true);
//		System.out.println(pas.get(0).getPrivate_activity_name());
//		System.out.println(((Page) pas).getTotal());
//		
//		pas = privateActivityService.selectActivityEnable(1, 10, "private_activity_name asc", true);
//		System.out.println(pas.get(0).getPrivate_activity_name());
//		System.out.println(((Page) pas).getTotal());
//		
//		pas = privateActivityService.selectActivityAll(1, 10, "private_activity_name desc", true);
//		System.out.println(pas.get(0).getPrivate_activity_name());
//		System.out.println(((Page) pas).getTotal());
//		
//		pas = privateActivityService.selectActivityAll(1, 10, "private_activity_name asc", true);
//		System.out.println(pas.get(0).getPrivate_activity_name());
//		System.out.println(((Page) pas).getTotal());
		
//		PublicActivity pa = publicActivityService.selectActivityById("2e955926-c28f-429e-a2b5-97e1b44db7f3");
//		System.out.println(pa.getPublic_activity_creator_name());
		
//		PublicActivity pa = new PublicActivity();
//		pa.setPublic_activity_id("2e955926-c28f-429e-a2b5-97e1b44db7f3");
//		pa.setPublic_activity_target(2000000);
//		int result = publicActivityService.updatePublicActivity(pa);
//		System.out.println(result);
		
//		List<String> pidList = new ArrayList<String>();
//		pidList.add(0, "a28c501e-dd7e-4622-ab92-6b3e357c823d");
//		pidList.add(1, "a73172b1-0476-4ca5-bb4c-04c3664dc27e");
//		
//		int result = publicActivityService.deletePublicActivityById(pidList);
//		System.out.println(result);
		
//		PrivateActivity pa = new PrivateActivity();
//		pa.setPrivate_activity_id(UUID.randomUUID().toString());
//		pa.setPrivate_activity_creator_id("09c18c679d2d41a0a29bb6f91df5010d");
//		pa.setPrivate_activity_name("第一次《心经》百万共修活动");
//		pa.setPrivate_activity_begin_time(DateUtils.parseStrToDate("2018-01-01", "yyyy-MM-dd"));
//		pa.setPrivate_activity_end_time(DateUtils.parseStrToDate("2018-12-31", "yyyy-MM-dd"));
//		pa.setPrivate_activity_target(1000000);
//		pa.setPrivate_activity_create_time(DateUtils.parseStrToDate("2017-11-20 08:32:15", "yyyy-MM-dd HH:mm:ss"));
//		pa.setPrivate_activity_enable(1);
//		
//		int result = privateActivityService.insertPrivateActivity(pa);
//		System.out.println(result);
		
//		List<PrivateActivity> pas = privateActivityService.selectActivityEnable();
//		System.out.println(pas.get(0).getPrivate_activity_creator_name());
//		
//		PrivateActivity pa = privateActivityService.selectActivityById("d17273df-2457-4c30-9194-6ddcf6b10b2d");
//		System.out.println(pa.getPrivate_activity_creator_name());
//		
//		PrivateActivity pa2 = new PrivateActivity();
//		pa2.setPrivate_activity_id("d17273df-2457-4c30-9194-6ddcf6b10b2d");
//		pa2.setPrivate_activity_target(2000000);
//		int result = privateActivityService.updatePrivateActivity(pa2);
//		System.out.println(result);
		
//		List<String> pidList = new ArrayList<String>();
//		pidList.add(0, "0cf27042-b734-4024-bfee-c9f4cf98d168");
//		pidList.add(1, "d19feb92-949c-4608-89e1-0e085c7c1a57");
//		
//		int result = privateActivityService.deletePrivateActivityById(pidList);
//		System.out.println(result);
		
//		Map<String, Integer> result = dynamicsService.platformDynamics("2018-09-18");
//		for(Map.Entry<String, Integer> entry : result.entrySet()) {
//			System.out.println(entry.getValue() + "------------" + entry.getKey());
//		}
		
//		List<PersonalCountOff> pco = personalCountOffService.selectTop100();
//		System.out.println(pco.get(0).getuName());
		
//		System.out.println(userInfoService.selectUserNumber(false));
		
//		int result = personalCountOffService.selectOneDayTotal("2018-07-25");
//		System.out.println(result);
//		
//		result = weeklyStatisticService.selectOneWeekTotal(2018, 9, 38, 3);
//		System.out.println(result);
//		
//		result = monthlyStatisticService.selectOneMonthTotal(2018, 9);
//		System.out.println(result);
//		
//		result = yearlyStatisticService.selectYearTotal(2018);
//		System.out.println(result);
		
//		int result = userInfoService.selectUserNumber(true);
//		System.out.println(result);
//		
//		result = userInfoService.selectUserNumber(false);
//		System.out.println(result);
		
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
