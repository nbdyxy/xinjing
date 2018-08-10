package cn.gzggzy.yyh.service.impl;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import cn.gzggzy.yyh.dao.UserInfoDao;
import cn.gzggzy.yyh.model.UserInfo;
import cn.gzggzy.yyh.redis.CacheExpire;
import cn.gzggzy.yyh.service.LoginService;

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
	

}
