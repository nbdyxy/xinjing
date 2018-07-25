package cn.gzggzy.yyh.controller;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
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
import org.springframework.web.servlet.ModelAndView;

import cn.gzggzy.yyh.config.Configuration;
import cn.gzggzy.yyh.filter.LoginFilter;
import cn.gzggzy.yyh.model.RegisterUserInfo;
import cn.gzggzy.yyh.model.UserInfo;
import cn.gzggzy.yyh.service.UserInfoService;
import cn.gzggzy.yyh.util.CookieUtil;
import cn.gzggzy.yyh.util.DESUtils;
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
			UserInfo userInfoModel = userInfoService.login(username, password);
			if (null != userInfoModel) {
				log.info("key: {}", configuration.getKey());
				String uid = userInfoModel.getUser_id();
				String randomId = UUID.randomUUID().toString().replace("-", "");
				String loginid = DESUtils.encrypt(randomId, configuration.getKey());
				tokenUtil.createToken(loginid, uid);//此部分可缓存必要的用户共修信息
				CookieUtil.setCookie(configuration.getLoginCookieName(), 6000, loginid);
				return "redirect:/user/"+loginid;
			} else {
				ObjectError error = new ObjectError("login", "用户名或密码错误");
				bindingResult.addError(error);
				return "gongxiu";
			}
		}
		
	}
	
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
					String randomId = UUID.randomUUID().toString().replace("-", "");
					String loginid = DESUtils.encrypt(randomId, configuration.getKey());
					tokenUtil.createToken(loginid, uid);//此部分可缓存必要的用户共修信息
					CookieUtil.setCookie(configuration.getLoginCookieName(), 6000, loginid);
					return "redirect:/user/"+loginid;
				}
			}
		}
		return "/register";
	}
	
	@GetMapping("/{secu_id}")
	public String userDetailInfo(@ModelAttribute("secu_id") String secu_id, UserInfo userInfo, BindingResult bindingResult) {
		log.info("secu_id: {}", secu_id);
		String uid = loginFilter.checkLogin();
		log.info("uid: {}", uid);
		if (null != uid) {
			return "gongxiu_personally";
		}
		ObjectError error = new ObjectError("register", "用户登录信息过期，请重新登录");
		bindingResult.addError(error);
		return "gongxiu";
	}
	
	@ResponseBody
	@GetMapping("/findAll")
	public List<UserInfo> findAllUser() {
		return userInfoService.selectUsers();
	}
	
	@ResponseBody
	@GetMapping("/findSingle")
	public UserInfo findUserByUserName(String userName) {
		System.out.println(userName);
		UserInfo info = userInfoService.selectUserByUserName(userName);
		return info;
	}
	
}
