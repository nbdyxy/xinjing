package cn.gzggzy.yyh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;

import cn.gzggzy.yyh.filter.LoginFilter;
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
		return loginFilter.checkLogin(bindingResult);
	}
	
	@GetMapping("/gongxiu_personally")
	public String gongxiu_personally() {
		return "gongxiu_personally";
	}
	
	
}
