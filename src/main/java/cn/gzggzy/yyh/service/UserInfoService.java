package cn.gzggzy.yyh.service;

import java.util.List;

import cn.gzggzy.yyh.model.RegisterUserInfo;
import cn.gzggzy.yyh.model.UserInfo;

/**
 * @author Administrator
 *
 */
public interface UserInfoService {
	
	/**
	 * @Title: insert  
	 * @Description: TODO  
	 * @param uid 
	 * @param RegisterUserInfo
	 * @return int
	 * @throws
	 */
	int insert(String uid, RegisterUserInfo RegisterUserInfo);
	
	List<UserInfo> selectUsers();
	
	List<String> selectAllUserName(String usernameList);
	
	UserInfo selectUserById(String uid);
	
	UserInfo selectUserByUserName(String userName);
	
	UserInfo login(String userName, String password, String randomId);
	
	UserInfo updateUserInfo(UserInfo userInfo, String randomId);
	/**
	 * 
	 * @Title: selectUserNumber  
	 * @Description: 查询满足条件的用户人数  
	 * @param boolean 是否参与过报数
	 * @return int 统计人数
	 * @throws
	 */
	int selectUserNumber(boolean isAttend);
}
