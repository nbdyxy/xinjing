package cn.gzggzy.yyh.controller;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;

import cn.gzggzy.yyh.config.Configuration;
import cn.gzggzy.yyh.model.UserInfo;
import cn.gzggzy.yyh.service.UserInfoService;
import cn.gzggzy.yyh.util.CookieUtil;
import cn.gzggzy.yyh.util.DESUtils;
import cn.gzggzy.yyh.util.TokenUtil;

@Controller
public class LoginController {
	
	private static final Logger log = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private UserInfoService userInfoService;
	
	@Autowired
	private TokenUtil tokenUtil;
	
	@Autowired
	private Configuration configuration;
	
	@PostMapping("/login")
	public String login(@Valid UserInfo userInfo, BindingResult bindingResult) {
		String username = userInfo.getUsername();
		String password = userInfo.getPassword();
		log.info("userName: {}", username);
		log.info("password: {}",  password);
		log.info("result: {}",  bindingResult.hasErrors());
		List<ObjectError> errors = bindingResult.getAllErrors();
		for(ObjectError error : errors) {
			log.info(error.toString());
		}
		if (bindingResult.hasErrors()) {
			return "gongxiu";
		} else {
			UserInfo userInfoModel = userInfoService.login(username, password);
			if (null != userInfoModel) {
				log.info("key: {}", configuration.getKey());
				String userid = userInfoModel.getUser_id();
				String uuid = UUID.randomUUID().toString().replace("-", "");
				String loginid = DESUtils.encrypt(userid + uuid, configuration.getKey());
				tokenUtil.createToken(loginid);//此部分可缓存必要的用户共修信息
				CookieUtil.setCookie(configuration.getLoginCookieName(), 6000, loginid);
				return "redirect:/gongxiu_personally";
			} else {
				ObjectError error = new ObjectError("login", "用户名或密码错误");
				bindingResult.addError(error);
				return "gongxiu";
			}
		}
		
	}
	
	
	
}
