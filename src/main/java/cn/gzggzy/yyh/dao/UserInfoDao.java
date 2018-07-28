package cn.gzggzy.yyh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.gzggzy.yyh.model.UserInfo;

public interface UserInfoDao {
	
	int insert(UserInfo userInfo);
	
	List<UserInfo> selectUsers();
	
	List<String> selectAllUserName();
	
	UserInfo selectUserByUserName(@Param("userName") String userName);
	
	UserInfo login(@Param("userName") String userName, @Param("password") String password);
	
}
