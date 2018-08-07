package cn.gzggzy.yyh.service;

import cn.gzggzy.yyh.model.UserInfo;

public interface LoginService {
	
	public UserInfo login(String userName, String password, String randomId);
	
}
