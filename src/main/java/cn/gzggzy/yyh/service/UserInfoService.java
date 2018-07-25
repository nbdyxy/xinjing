package cn.gzggzy.yyh.service;

import java.util.List;

import cn.gzggzy.yyh.model.RegisterUserInfo;
import cn.gzggzy.yyh.model.UserInfo;

public interface UserInfoService {
	
	int insert(String uid, RegisterUserInfo RegisterUserInfo);
	
	List<UserInfo> selectUsers();
	
	UserInfo selectUserByUserName(String userName);
	
	UserInfo login(String userName, String password);
	
}
