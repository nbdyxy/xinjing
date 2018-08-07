package cn.gzggzy.yyh.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import cn.gzggzy.yyh.config.Configuration;
import cn.gzggzy.yyh.model.UserInfo;
import cn.gzggzy.yyh.service.LoginService;
import cn.gzggzy.yyh.service.UserInfoService;
import cn.gzggzy.yyh.util.CookieUtil;
import cn.gzggzy.yyh.util.DESUtils;

@Controller
public class LoginController {
	
	private static final Logger log = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private UserInfoService userInfoService;
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private Configuration configuration;
	
	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	
	@PostMapping("/login")
	public String login(@Valid UserInfo userInfo, BindingResult bindingResult) {
		log.info("userInfo: {}", userInfo.toString());
		List<ObjectError> errors = bindingResult.getAllErrors();
		for(ObjectError error : errors) {
			log.info(error.toString());
		}
		if (bindingResult.hasErrors()) {
			return "gongxiu";
		} else {
			String username = userInfo.getUsername();
			String password = DESUtils.encrypt(userInfo.getPassword(), configuration.getKey());
			String randomId = RandomStringUtils.randomAlphanumeric(8);
			UserInfo userInfoModel = loginService.login(username, password, randomId);
			if (null != userInfoModel) {
				log.info("key: {}", configuration.getKey());
//				String uid = userInfoModel.getUser_id();
//				tokenUtil.createToken(randomId, uid);//此部分可缓存必要的用户共修信息
				CookieUtil.setCookie(configuration.getLoginCookieName(), 6000, randomId);
				return "redirect:/user";
			} else {
				ObjectError error = new ObjectError("login", "用户名或密码错误");
				bindingResult.addError(error);
				return "gongxiu";
			}
		}
		
	}
	
	@GetMapping("/logout")
	public String loginOut(UserInfo userInfo) {
		String randomId = CookieUtil.getCookie(configuration.getLoginCookieName());
		if (null != randomId) {
			boolean deleteResult = redisTemplate.delete(randomId);
			log.info("{} delete redis result {}.", randomId, deleteResult);
		}
		CookieUtil.setCookie(configuration.getLoginCookieName(), 0, "");
		return "redirect:/login";
	}
}
