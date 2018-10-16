package cn.gzggzy.yyh.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;

import cn.gzggzy.yyh.filter.LoginFilter;
import cn.gzggzy.yyh.model.PersonalCountOff;
import cn.gzggzy.yyh.model.PublicActivity;
import cn.gzggzy.yyh.model.RegisterUserInfo;
import cn.gzggzy.yyh.model.UserInfo;
import cn.gzggzy.yyh.service.DynamicsService;
import cn.gzggzy.yyh.service.PersonalCountOffService;
import cn.gzggzy.yyh.service.PublicActivityService;
import cn.gzggzy.yyh.util.DateUtils;

@Controller
public class RouterController {

	@Autowired
	public LoginFilter loginFilter;
	
	@Autowired
	public DynamicsService dynamicsService;
	
	@Autowired
	public PersonalCountOffService personalCountOffService;
	
	@Autowired
	public PublicActivityService publicActivityService;
	
	@GetMapping("/index")
	public String index() {
		return "index";
	}
	
	@GetMapping("/dongtai")
	public String dongtai(Model model) {
//		Calendar c = Calendar.getInstance();
//		String date = DateUtils.parseDateToStr(c.getTime(), "yyyy-MM-dd");
		Map<String, Integer> platformDynamics = dynamicsService.platformDynamics("2018-09-18");
		List<PersonalCountOff> top100 = personalCountOffService.selectTop100();
		List<PublicActivity> paList =  publicActivityService.selectActivityEnable(0, 0, null, false);
		model.addAttribute("platformDynamics", platformDynamics);
		model.addAttribute("top100", top100);
		model.addAttribute("paList", paList);
		if ( 0 != paList.size()) {
			PublicActivity pa = paList.get(0);
			Date begin = pa.getPublic_activity_begin_time();
			Date end = pa.getPublic_activity_end_time();
			Calendar c = Calendar.getInstance();
			Long days = DateUtils.getDistanceDays(begin, c.getTime()) + 1;
			Long totalDays = DateUtils.getDistanceDays(begin, end) + 1;
			System.out.println(totalDays);
			Double timePercent = days.doubleValue() / totalDays.doubleValue() * 100;
			int timePercentINT = (int) Math.ceil(timePercent);
			model.addAttribute("days", "第" + days + "日");
			model.addAttribute("timePercent", timePercentINT);
		}
		return "dongtai";
	}
	
	@GetMapping("/huixiang")
	public String huixiang() {
		return "huixiang";
	}
	
	@GetMapping("/login")
	public String gongxiu(UserInfo userInfo, BindingResult bindingResult) {
		Map<String, Object> userInfoRedis = loginFilter.checkLogin();
		if (0 != userInfoRedis.size()) {
			return "redirect:/user"; 
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
