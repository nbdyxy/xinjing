package cn.gzggzy.yyh.dao;

import java.util.List;

import cn.gzggzy.yyh.model.UserInfo;

public interface UserInfoDao {
	
	int insert(UserInfo userInfo);
	
	List<UserInfo> selectUsers();
	
	UserInfo selectUserByUserName(String userName);
	
}
