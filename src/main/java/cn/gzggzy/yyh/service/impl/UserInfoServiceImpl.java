package cn.gzggzy.yyh.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import cn.gzggzy.yyh.config.Configuration;
import cn.gzggzy.yyh.dao.UserInfoDao;
import cn.gzggzy.yyh.model.RegisterUserInfo;
import cn.gzggzy.yyh.model.UserInfo;
import cn.gzggzy.yyh.redis.CacheExpire;
import cn.gzggzy.yyh.service.UserInfoService;
import cn.gzggzy.yyh.util.DESUtils;

/**
 * @author Administrator
 *
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {
	
	private static final Logger logger = LoggerFactory.getLogger(UserInfoServiceImpl.class);

	@Autowired
	private UserInfoDao userInfoDao;
	
	@Autowired
	private Configuration configuration;
	
	/*
	 * @see cn.gzggzy.yyh.service.UserInfoService#insert(java.lang.String, cn.gzggzy.yyh.model.RegisterUserInfo)
	 */
	@Override
	public int insert(String uid, RegisterUserInfo registerUserInfo) {
		
		Date now = Calendar.getInstance().getTime();
		
		UserInfo userInfo = new UserInfo();
		userInfo.setUser_id(uid);
		userInfo.setUsername(registerUserInfo.getUsername());
		userInfo.setPassword(DESUtils.encrypt(registerUserInfo.getPassword(), configuration.getKey()));
		userInfo.setFirst_login_time(now);
		userInfo.setLast_login_time(now);
		userInfo.setLogin_count(1);
		userInfo.setLast_login_ip("192.168.70.11");
		userInfo.setIs_enable(1);
		userInfo.setCreate_time(now);
		userInfo.setThird_party_enable(0);
		
		return userInfoDao.insert(userInfo);
	}
	
	/*
	 * @see cn.gzggzy.yyh.service.UserInfoService#selectUsers()
	 */
	@Override
	public List<UserInfo> selectUsers() {
		logger.info("查询所有人");
		return userInfoDao.selectUsers();
	}

	/*
	 * @see cn.gzggzy.yyh.service.UserInfoService#selectAllUserName(java.lang.String)
	 */
	@Override
	@Cacheable(value = "user", key = "#usernameList", unless="#result == null")
	@CacheExpire(expire = 6000)
	public List<String> selectAllUserName(String usernameList) {
		return userInfoDao.selectAllUserName();
	}
	
	/*
	 * @see cn.gzggzy.yyh.service.UserInfoService#selectUserById(java.lang.String)
	 */
	@Override
	public UserInfo selectUserById(String uid) {
		logger.info("按uid查询人员: {}", uid);
		return userInfoDao.selectUserById(uid);
	}
	
	/*
	 * @see cn.gzggzy.yyh.service.UserInfoService#selectUserByUserName(java.lang.String)
	 */
	@Override
	public UserInfo selectUserByUserName(String userName) {
		logger.info("按姓名查询人员");
		return userInfoDao.selectUserByUserName(userName);
	}
	
	/*
	 * @see cn.gzggzy.yyh.service.UserInfoService#login(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	@Cacheable(value = "user", key = "#randomId", unless="#result == null")
	@CacheExpire(expire = 6000)
	public UserInfo login(String userName, String password, String randomId) {
		return userInfoDao.login(userName, password);
	}
	
	/*
	 * @see cn.gzggzy.yyh.service.UserInfoService#updateUserInfo(cn.gzggzy.yyh.model.UserInfo, java.lang.String)
	 */
	@Override
	@CachePut(cacheNames = "user", key="#randomId", unless="#result == null")
	public UserInfo updateUserInfo(UserInfo userInfo, String randomId) {
		//更新数据库的用户信息
		int update = userInfoDao.updateUserInfo(userInfo);
		if (1 == update) {
			//获取最新的用户信息
			String uid = userInfo.getUser_id();
			userInfo = userInfoDao.selectUserById(uid);
			return userInfo;
		}
		return null;
	}
	
	/*
	 * @see cn.gzggzy.yyh.service.UserInfoService#selectUserNumber(boolean)
	 */
	@Override
	public int selectUserNumber(boolean isAttend) {
		if (isAttend) {
			return userInfoDao.selectUserNumber(1);
		} else {
			return userInfoDao.selectUserNumber(null);
		}
	}
	
	
	
}
