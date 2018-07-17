package cn.gzggzy.yyh.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;

import cn.gzggzy.yyh.model.UserInfo;

@Controller
public class LoginController {
	
	private static final Logger log = LoggerFactory.getLogger(LoginController.class);

	
	@GetMapping("/login")
	public String login(@Valid UserInfo userInfo, BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			return "/gongxiu";
		}

		return "/gongxiu_personally";
	}
	
	
}
