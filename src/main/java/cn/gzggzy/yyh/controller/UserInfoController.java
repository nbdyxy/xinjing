package cn.gzggzy.yyh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.gzggzy.yyh.model.UserInfo;
import cn.gzggzy.yyh.service.UserInfoService;

@RestController
@RequestMapping("/user")
public class UserInfoController {
	
	private UserInfoService userInfoService;
	
	@Autowired
	public UserInfoController(UserInfoService userInfoService) {
		this.userInfoService = userInfoService;
	}
	
	@ResponseBody
	@GetMapping("/add")
	public int addUser(UserInfo userInfo) {
		return userInfoService.insert(userInfo);
	}
	
	@ResponseBody
	@GetMapping("/findAll")
	public List<UserInfo> findAllUser() {
		return userInfoService.selectUsers();
	}
	
	@ResponseBody
	@GetMapping("/findSingle")
	public UserInfo findUserByUserName(String userName) {
		return userInfoService.selectUserByUserName(userName);
	}
	
}
