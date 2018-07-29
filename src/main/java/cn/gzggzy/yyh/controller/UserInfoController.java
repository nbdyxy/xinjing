package cn.gzggzy.yyh.controller;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.gzggzy.yyh.config.Configuration;
import cn.gzggzy.yyh.filter.LoginFilter;
import cn.gzggzy.yyh.model.RegisterUserInfo;
import cn.gzggzy.yyh.model.UserInfo;
import cn.gzggzy.yyh.service.UserInfoService;
import cn.gzggzy.yyh.util.CookieUtil;
import cn.gzggzy.yyh.util.TokenUtil;

@Controller
@RequestMapping("/user")
public class UserInfoController {
	
	private static final Logger log = LoggerFactory.getLogger(UserInfoController.class);
	
	@Autowired
	private UserInfoService userInfoService;
	
	@Autowired
	private TokenUtil tokenUtil;
	
	@Autowired
	private Configuration configuration;
	
	@Autowired
	public LoginFilter loginFilter;
	
	
	@PostMapping("/add")
	public String addUser(@Valid RegisterUserInfo registerUserInfo, BindingResult bindingResult) {
		log.info("registerUserInfo: {}", registerUserInfo.toString());
		List<ObjectError> errors = bindingResult.getAllErrors();
		for(ObjectError error : errors) {
			log.info(error.toString());
		}
		if (bindingResult.hasErrors()) {
			return "/register";
		} else {
			String password = registerUserInfo.getPassword();
			String confirmpassword = registerUserInfo.getConfirmpassword();
			if (!password.equals(confirmpassword)) {
				ObjectError error = new ObjectError("register", "两次输入的密码不一致");
				bindingResult.addError(error);
				return "/register";
			} else {
				String uid = UUID.randomUUID().toString().replace("-", "");
				int result = userInfoService.insert(uid, registerUserInfo);
				if (result == 1) {
					String randomId = RandomStringUtils.randomAlphabetic(8);
					tokenUtil.createToken(randomId, uid);//此部分可缓存必要的用户共修信息
					CookieUtil.setCookie(configuration.getLoginCookieName(), 6000, randomId);
					return "redirect:/user/"+randomId;
				}
			}
		}
		return "/register";
	}
	
	@GetMapping("/{randomId}")
	public String userDetailInfo(Model model, @ModelAttribute("randomId") String randomId, UserInfo userInfo, BindingResult bindingResult) {
		log.info("randomId: {}", randomId);
		String[] loginInfo = loginFilter.checkLogin();
		if (null != loginInfo[1]) {
			log.info("uid: {}, 用户登录信息校验通过.", loginInfo[1]);
			userInfo = userInfoService.selectUserById(loginInfo[1].replace("\"", ""));
			log.info("userInfo: {}", userInfo);
			model.addAttribute("userInfo", userInfo);
			return "gongxiu_personally";
		}
		log.info("用户登录信息过期.");
		ObjectError error = new ObjectError("register", "用户登录信息过期，请重新登录");
		bindingResult.addError(error);
		return "redirect:/login";
	}
	
	@ResponseBody
	@GetMapping("/findAll")
	public List<UserInfo> findAllUser() {
		return userInfoService.selectUsers();
	}
	
	@PostMapping("/checkname/{username}")
	@ResponseBody
	public boolean checkname(@PathVariable("username") String username) {
		log.info("username: {}", username);
		List<String> usernameList = userInfoService.selectAllUserName("usernameList");
		for (String name : usernameList) {
			if (name.equals(username)) {
				return false;
			}
		}
		return true;
	}
	
	
	@ResponseBody
	@GetMapping("/findSingle")
	public UserInfo findUserByUserName(String userName) {
		System.out.println(userName);
		UserInfo info = userInfoService.selectUserByUserName(userName);
		return info;
	}
	
}
