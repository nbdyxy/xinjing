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
		Calendar c = Calendar.getInstance();
		String date = DateUtils.parseDateToStr(c.getTime(), "yyyy-MM-dd");
		Map<String, Integer> platformDynamics = dynamicsService.platformDynamics(date);
		List<PersonalCountOff> top100 = personalCountOffService.selectTop100();
		List<PublicActivity> paList =  publicActivityService.selectActivityEnable(0, 0, null, false);
		model.addAttribute("platformDynamics", platformDynamics);
		model.addAttribute("top100", top100);
		model.addAttribute("paList", paList);
		if ( 0 != paList.size()) {
			PublicActivity pa = paList.get(0);
			String public_activity_id = pa.getPublic_activity_id();
			
			//当前天数以及时间进度的计算
			Date begin = pa.getPublic_activity_begin_time();
			Date end = pa.getPublic_activity_end_time();
			Long days = DateUtils.getDistanceDays(begin, c.getTime()) + 1;
			Long totalDays = DateUtils.getDistanceDays(begin, end) + 1;
			Double timePercent = days.doubleValue() / totalDays.doubleValue() * 100;
			int timePercentINT = (int) Math.ceil(timePercent);
			
			//报数总量
			Double targetNumber = pa.getPublic_activity_target() + 0d;
			Double nowNumber = personalCountOffService.selectPublicActivityCountNumber(public_activity_id) + 0d;
			Double countPercent = nowNumber / targetNumber * 100; 
			int countPercentINT = (int) Math.ceil(countPercent);
			
			//参与当前活动的报数人数
			int joinNumber = personalCountOffService.selectActivityJoinNumber(public_activity_id);
			
			model.addAttribute("days", days);
			model.addAttribute("targetNumber", targetNumber.intValue());
			model.addAttribute("nowNumber", nowNumber.intValue());
			model.addAttribute("joinNumber", joinNumber);
			model.addAttribute("timePercentINT", timePercentINT);
			model.addAttribute("countPercentINT", countPercentINT);
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
