package cn.gzggzy.yyh.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.gzggzy.yyh.dao.UserInfoDao;
import cn.gzggzy.yyh.model.UserInfo;
import cn.gzggzy.yyh.service.UserInfoService;

@Service
public class UserInfoServiceImpl implements UserInfoService {
	
	private static final Logger logger = LoggerFactory.getLogger(UserInfoServiceImpl.class);
	
	private final UserInfoDao userInfoDao;
	
	@Autowired
    public UserInfoServiceImpl(UserInfoDao userInfoDao) {
        this.userInfoDao = userInfoDao;
    }
	
	@Override
	public int insert(UserInfo userInfo) {
		logger.info("插入数据");
		return userInfoDao.insert(userInfo);
	}

	@Override
	public List<UserInfo> selectUsers() {
		logger.info("查询所有人");
		return userInfoDao.selectUsers();
	}

	@Override
	public UserInfo selectUserByUserName(String userName) {
		logger.info("按姓名查询人员");
		System.out.println(userName);
		return userInfoDao.selectUserByUserName(userName);
	}

}
