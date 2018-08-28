package cn.gzggzy.yyh.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import cn.gzggzy.yyh.dao.UserInfoDao;
import cn.gzggzy.yyh.model.UserInfo;
import cn.gzggzy.yyh.redis.CacheExpire;
import cn.gzggzy.yyh.service.LoginService;
import cn.gzggzy.yyh.util.DateUtils;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private UserInfoDao userInfoDao;
	
	@Override
	@Cacheable(value = "user", key = "#p2", unless="#result == null")
	@CacheExpire(6000)
	public UserInfo login(String userName, String password, String randomId) {
		UserInfo userInfo = userInfoDao.login(userName, password);
		if (null != userInfo) {
			Date date = Calendar.getInstance().getTime();
			int login_count = userInfo.getLogin_count() + 1;
			userInfo.setLogin_count(login_count);
			userInfo.setLast_login_ip("192.168.70.11");
//			userInfo.setCreate_time(date);
//			userInfo.setFirst_login_time(date);
			userInfo.setLast_login_time(date);
			System.out.println(userInfo);
			System.out.println(userInfo.getCreate_time());
			int insert = userInfoDao.updateUserInfo(userInfo);
			if (1 == insert) {
				return userInfo;
			}
		}
		return null;
	}
	
	public static void main(String[] args) {
		Calendar c = Calendar.getInstance();
//		c.set(2018, 11, 31);
		System.out.println(c.getTime());
		System.out.println(c.getFirstDayOfWeek());
		c.setFirstDayOfWeek(Calendar.MONDAY);
		//每个星期的第几天
		int a = c.get(Calendar.DAY_OF_WEEK);
		//每个月的第几个星期
		int b = c.get(Calendar.WEEK_OF_MONTH);
		//每年的第几个星期
		int year_week = c.get(Calendar.WEEK_OF_YEAR);
		if (1 == a) {
			a += 6;
		} else {
			a--;
		}
		System.out.println("每个星期的第几天: " + a);
		System.out.println("每个月的第几个星期: " + b);
		System.out.println("每年的第几个星期: " + year_week);
		System.out.println(c.getActualMinimum(Calendar.DAY_OF_MONTH));
		int firstDayOfWeek = c.getFirstDayOfWeek();
		System.out.println(firstDayOfWeek);
		
		c.set(2019, 0, 8);
		System.out.println(c.getTime());
		System.out.println(DateUtils.getFirstDayOfWeek(c.getTime()));
		System.out.println(DateUtils.getLastDayOfWeek(c.getTime()));
		System.out.println(DateUtils.getWeekthOfYear(c.getTime()));
		System.out.println(DateUtils.getWeekthOfYear(c.getTime()));
		System.out.println(c.get(Calendar.WEEK_OF_MONTH));
	}

}
