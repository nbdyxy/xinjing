package cn.gzggzy.yyh.service;

import java.util.List;

import cn.gzggzy.yyh.model.RegisterUserInfo;
import cn.gzggzy.yyh.model.UserInfo;

public interface UserInfoService {
	
	int insert(String uid, RegisterUserInfo RegisterUserInfo);
	
	List<UserInfo> selectUsers();
	
	List<String> selectAllUserName(String usernameList);
	
	UserInfo selectUserById(String uid);
	
	UserInfo selectUserByUserName(String userName);
	
	UserInfo login(String userName, String password, String randomId);
	
	UserInfo updateUserInfo(UserInfo userInfo, String randomId);
	
}
