package cn.gzggzy.yyh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;

import cn.gzggzy.yyh.filter.LoginFilter;
import cn.gzggzy.yyh.model.RegisterUserInfo;
import cn.gzggzy.yyh.model.UserInfo;

@Controller
public class RouterController {

	@Autowired
	public LoginFilter loginFilter;
	
	@GetMapping("/index")
	public String index() {
		return "index";
	}
	
	@GetMapping("/dongtai")
	public String dongtai() {
		return "dongtai";
	}
	
	@GetMapping("/huixiang")
	public String huixiang() {
		return "huixiang";
	}
	
	@GetMapping("/login")
	public String gongxiu(UserInfo userInfo, BindingResult bindingResult) {
		String[] loginInfo = loginFilter.checkLogin();
		if (null != loginInfo[1]) {
			StringBuilder sb = new StringBuilder("redirect:/user/");
			sb.append(loginInfo[0]);
			return sb.toString(); 
		}
//		ObjectError error = new ObjectError("register", "用户登录信息过期，请重新登录");
//		bindingResult.addError(error);
		return "gongxiu";
	}
	
//	@GetMapping("/gongxiu_personally")
//	public String gongxiu_personally() {
//		return "gongxiu_personally";
//	}
	
	@GetMapping("/register")
	public String register(RegisterUserInfo registerUserInfo, BindingResult bindingResult) {
		return "register";
	}
	
	
}
