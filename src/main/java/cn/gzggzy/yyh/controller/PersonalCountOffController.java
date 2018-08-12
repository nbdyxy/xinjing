package cn.gzggzy.yyh.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.gzggzy.yyh.filter.LoginFilter;
import cn.gzggzy.yyh.model.PersonalCountOff;
import cn.gzggzy.yyh.model.UserInfo;
import cn.gzggzy.yyh.response.bean.RestResponseHashMap;
import cn.gzggzy.yyh.service.PersonalCountOffService;

@Controller
@RequestMapping("/personalcountoff")
public class PersonalCountOffController {
	
	@Autowired
	private PersonalCountOffService personalCountOffService;
	
	@Autowired
	public LoginFilter loginFilter;
	
	@PostMapping("/countoff")
	@ResponseBody
	public RestResponseHashMap saveOrUpdate(PersonalCountOff personalCountOff) {
		Map<String, Object> userInfoRedis = loginFilter.checkLogin();
		if (0 != userInfoRedis.size()) {
			String randomId = userInfoRedis.get("randomId").toString();
			String uid = ((UserInfo) userInfoRedis.get("userInfo")).getUser_id();
			List<PersonalCountOff> personalCountOffList = personalCountOffService.updateTopFive(personalCountOff, randomId, uid);
			if (0 != personalCountOffList.size()) {
				return RestResponseHashMap.success("报数成功", personalCountOffList);
			}
		}
		return RestResponseHashMap.success("报数失败", null);
		
	}
	
//	@GetMapping("/selectTopFive/{uid}")
//	@ResponseBody
//	public List<PersonalCountOff> selectTopFive(@PathVariable String uid) {
//		Map<String, Object> userInfoRedis = loginFilter.checkLogin();
//		if (0 != userInfoRedis.size()) {
//			String randomId = userInfoRedis.get("randomId").toString();
//			List<PersonalCountOff> personalCountOffList = personalCountOffService.handleTopFive(uid, randomId); 
//			return personalCountOffService.selectTopFive(uid);
//		}
//	}
	
	@GetMapping("/history")
	public String selectAll(Model model) {
		Map<String, Object> userInfoRedis = loginFilter.checkLogin();
		if (0 != userInfoRedis.size()) {
			String uid = ((UserInfo) userInfoRedis.get("userInfo")).getUser_id();
			List<String> monthIndex = personalCountOffService.selectHistoryMonthIndex(uid);
			List<PersonalCountOff> perMonthRecord = personalCountOffService.selectHistoryPerMonth(uid, monthIndex.get(0));
			model.addAttribute("monthIndex", monthIndex);
			model.addAttribute("perMonthRecord", perMonthRecord);
			return "history";
		}
		return "redirect:/login";
	}
	
	@PostMapping("/getPerMonthRecord")
	@ResponseBody
	public RestResponseHashMap getPerMonthRecord(String date) {
		Map<String, Object> userInfoRedis = loginFilter.checkLogin();
		if (0 != userInfoRedis.size()) {
			String uid = ((UserInfo) userInfoRedis.get("userInfo")).getUser_id();
			List<PersonalCountOff> perMonthRecord = personalCountOffService.selectHistoryPerMonth(uid, date);
			if (0 != perMonthRecord.size()) {
				return RestResponseHashMap.success("查询成功", perMonthRecord);
			}
		}
		return RestResponseHashMap.success("查询失败，请重试", null);
	}
}
