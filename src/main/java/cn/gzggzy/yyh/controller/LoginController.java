package cn.gzggzy.yyh.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;

import cn.gzggzy.yyh.model.UserInfo;
import cn.gzggzy.yyh.service.UserInfoService;

@Controller
public class LoginController {
	
	private static final Logger log = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private UserInfoService userInfoService;
	
	@PostMapping("/login")
	public String login(@Valid UserInfo userInfo, BindingResult bindingResult) {
		String username = userInfo.getUsername();
		String password = userInfo.getPassword();
		log.info("userName: " + username);
		log.info("password: " + password);
		log.info("result: " + bindingResult.hasErrors());
		List<ObjectError> errors = bindingResult.getAllErrors();
		for(ObjectError error : errors) {
			log.info(error.toString());
		}
		if (bindingResult.hasErrors()) {
			return "gongxiu";
		} else {
			UserInfo userInfoModel = userInfoService.login(username, password);
			if (null != userInfoModel) {
				return "redirect:/gongxiu_personally";
			} else {
				ObjectError error = new ObjectError("login", "用户名或密码错误");
				bindingResult.addError(error);
				return "gongxiu";
			}
		}
		
	}
	
}
