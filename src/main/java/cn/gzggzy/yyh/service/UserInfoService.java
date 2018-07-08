package cn.gzggzy.yyh.service;

import java.util.List;

import cn.gzggzy.yyh.model.UserInfo;

public interface UserInfoService {
	
	int insert(UserInfo userInfo);
	
	List<UserInfo> selectUsers();
	
	UserInfo selectUserByUserName(String userName);
	
}
