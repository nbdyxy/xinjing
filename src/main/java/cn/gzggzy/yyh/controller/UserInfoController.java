package cn.gzggzy.yyh.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.gzggzy.yyh.config.Configuration;
import cn.gzggzy.yyh.filter.LoginFilter;
import cn.gzggzy.yyh.model.PersonalCountOff;
import cn.gzggzy.yyh.model.RegisterUserInfo;
import cn.gzggzy.yyh.model.UserInfo;
import cn.gzggzy.yyh.response.bean.RestResponseHashMap;
import cn.gzggzy.yyh.service.PersonalCountOffService;
import cn.gzggzy.yyh.service.PersonalStatisticService;
import cn.gzggzy.yyh.service.UserInfoService;
import cn.gzggzy.yyh.util.CookieUtil;
import cn.gzggzy.yyh.util.DESUtils;
import cn.gzggzy.yyh.util.DateUtils;
import cn.gzggzy.yyh.util.TokenUtil;

@Controller
@RequestMapping("/user")
public class UserInfoController {
	
	private static final Logger log = LoggerFactory.getLogger(UserInfoController.class);
	
	@Autowired
	private UserInfoService userInfoService;
	
	@Autowired
	private PersonalCountOffService personalCountOffService;
	
	@Autowired
	private PersonalStatisticService personalStatisticService;
	
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
			String regex = "^([\\u4E00-\\u9FA50-9a-zA-Z_]{2,20}+)$";
			if (!registerUserInfo.getUsername().matches(regex)) {
				ObjectError error = new ObjectError("register", "法名仅支持2-20位中英文、数字和下划线,且不能有空格！");
				bindingResult.addError(error);
				return "/register";
			}else if (!password.equals(confirmpassword)) {
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
					return "redirect:/user";
				}
			}
		}
		return "/register";
		
	}
	
	@GetMapping
	public String userDetailInfo(Model model, UserInfo userInfo, BindingResult bindingResult) {
		Map<String, Object> userInfoRedis = loginFilter.checkLogin();
		if (0 != userInfoRedis.size()) {
			userInfo = (UserInfo) userInfoRedis.get("userInfo");
			String randomId = userInfoRedis.get("randomId").toString();
			String uid = userInfo.getUser_id();
			List<PersonalCountOff> personalCountOffTopFive = personalCountOffService.handleTopFive(uid, randomId);
			Date date = new Date();
			String dateStr = DateUtils.parseDateToStr(date, "yyyy-MM-dd");
			//获取当前日期所在周、月、年的报数汇总
			Map<String, Object> statisticMap = personalStatisticService.personalTotal(uid, date, dateStr);
			log.info("userInfo: {}", userInfo);
			log.info("weekly_total: {}", ((Map)statisticMap.get("ws_map")));
			model.addAttribute("userInfo", userInfo);
			model.addAttribute("personalCountOffTopFive", personalCountOffTopFive);
			model.addAttribute("statisticMap", statisticMap);
			model.addAttribute("serverTime", dateStr);
			return "gongxiu_personally";
		}
		log.info("用户登录信息过期.");
		ObjectError error = new ObjectError("register", "用户登录信息过期，请重新登录");
		bindingResult.addError(error);
		return "redirect:/login";
	}
	
	@GetMapping("/info")
	public String personInfo(Model model, UserInfo userInfo) {
		Map<String, Object> userInfoRedis = loginFilter.checkLogin();
		if (0 != userInfoRedis.size()) {
			log.info("userInfo: {}, 用户登录信息校验通过.", userInfo);
			userInfo = (UserInfo)userInfoRedis.get("userInfo");
			model.addAttribute("userInfo", userInfo);
			return "person_info";
		}
		return "redirect:/login";
	}
	
	@ResponseBody
	@GetMapping("/findAll")
	public List<UserInfo> findAllUser() {
		return userInfoService.selectUsers();
	}
	
	@PostMapping("/checkname/{username}")
	@ResponseBody
	public RestResponseHashMap checkname(@PathVariable("username") String username) {
		log.info("username: {}", username);
		List<String> usernameList = userInfoService.selectAllUserName("usernameList");
		for (String name : usernameList) {
			if (name.equals(username)) {
				return RestResponseHashMap.success("校验完成", false);
			}
		}
		return RestResponseHashMap.success("校验完成", true);
	}
	
	@ResponseBody
	@PostMapping("/updateUserInfo")
	public RestResponseHashMap updateUserInfo(UserInfo userInfo) {
		Map<String, Object> userInfoRedis = loginFilter.checkLogin();
		if (0 != userInfoRedis.size()) {
			userInfo.setUser_id(((UserInfo)userInfoRedis.get("userInfo")).getUser_id());
			log.info("UserInfo: {}", userInfo);
			UserInfo result =  userInfoService.updateUserInfo(userInfo, userInfoRedis.get("randomId").toString());
			if ( null != result) {
				return RestResponseHashMap.success("更新完成", userInfo);
			}
		}
		return RestResponseHashMap.error("更新失败", null);
	}
	
	@ResponseBody
	@PostMapping("/updatePassword")
	public RestResponseHashMap updatePassword(String oldpassword, String password, String confirmpassword) {
		Map<String, Object> userInfoRedis = loginFilter.checkLogin();
		String result = "密码不符合规则！";
		log.info("oldpassword: {} - password: {} - confirmpassword: {}", oldpassword, password, confirmpassword);
		/*对参数进行正则校验，检验规则长度 6-20，只能包含英文字母、数字、英文特殊字符*/
		String regStr = "^([0-9a-zA-Z/^/$/.//,;:'!@#%&/*/|/?/+/(/)/[/]/{/}]{6,20}+)$";
		//校验登录状态
		if (0 != userInfoRedis.size()) { 
			if (null != oldpassword && null != password && null != confirmpassword) {
				if (oldpassword.matches(regStr) && password.matches(regStr) && confirmpassword.matches(regStr)) {
					//检验后两次密码是否一致
					if (password.equals(confirmpassword)) {
						//检验旧密码是否正确
						UserInfo userInfo = (UserInfo) userInfoRedis.get("userInfo");
						String randomId = userInfoRedis.get("randomId").toString();
						String en_oldpassword = DESUtils.encrypt(oldpassword, configuration.getKey());
						String en_password = DESUtils.encrypt(password, configuration.getKey());
						if (en_oldpassword.equals(userInfo.getPassword())) {
							UserInfo newUserInfo = new UserInfo();
							newUserInfo.setUser_id(userInfo.getUser_id());
							newUserInfo.setPassword(en_password);
							UserInfo updateResult = userInfoService.updateUserInfo(newUserInfo, randomId);
							if (null != updateResult) {
								result = "密码修改成功！";
							} else {
								result = "密码修改异常，请联系管理员！";
							}
						} else {
							result = "请检查原密码是否输入正确！";
						}
					} else {
						result = "两次输入的密码不一致！";
					}
				}
			}
		}
		return RestResponseHashMap.success(result, null);
	}
	
	@ResponseBody
	@GetMapping("/findSingle")
	public UserInfo findUserByUserName(String userName) {
		System.out.println(userName);
		UserInfo info = userInfoService.selectUserByUserName(userName);
		return info;
		
	}
	
}
