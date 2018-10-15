package cn.gzggzy.yyh.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import cn.gzggzy.yyh.model.YearlyStatistic;
import cn.gzggzy.yyh.response.bean.RestResponseHashMap;
import cn.gzggzy.yyh.service.PersonalCountOffService;
import cn.gzggzy.yyh.service.RankService;
import cn.gzggzy.yyh.service.StatisticService;
import cn.gzggzy.yyh.util.DateUtils;

@Controller
@RequestMapping("/personalcountoff")
public class PersonalCountOffController {
	
	private final static Logger log = LoggerFactory.getLogger(PersonalCountOffController.class);
	
	@Autowired
	private PersonalCountOffService personalCountOffService;
	
	@Autowired
	private StatisticService statisticService;
	
	@Autowired
	private RankService rankService;
	
	@Autowired
	public LoginFilter loginFilter;
	
	@PostMapping("/countoff")
	@ResponseBody
	public RestResponseHashMap saveOrUpdate(PersonalCountOff personalCountOff) {
		Map<String, Object> userInfoRedis = loginFilter.checkLogin();
		if (0 != userInfoRedis.size()) {
			String randomId = userInfoRedis.get("randomId").toString();
			String uid = ((UserInfo) userInfoRedis.get("userInfo")).getUser_id();
			//获取之前缓存的状态用于更新的判断
			List<PersonalCountOff> pcolBefore = personalCountOffService.handleTopFive(uid, randomId);
			Date date = new Date();
			String dateStr = DateUtils.parseDateToStr(date, "yyyyMMdd");
			List<PersonalCountOff> personalCountOffList = personalCountOffService.updateTopFive(personalCountOff, randomId, uid, date);
			if (0 != personalCountOffList.size()) {
				//同步更新周度、月度、年度汇总表
				Map<String, Object> statisticMap = statisticService.saveOrUpdateStatistic(pcolBefore.get(0), randomId, uid, date, dateStr, personalCountOff.getRecord_number());
				Map<String, Object> resultMap = new HashMap<String, Object>();
				Map<String, Object> rankMap = rankService.reverRankMapCountOff(uid, date, personalCountOffList.get(0).getRecord_number(), statisticMap);
				Date firstDayOfYear = DateUtils.getFirstDayOfYear(date);
				long day = DateUtils.getDistanceDays(firstDayOfYear, date) + 1;
				Double year_avg = 0d;
				YearlyStatistic ys = (YearlyStatistic) statisticMap.get("ys");
				if (null != ys) {
					Double total = new Double(ys.getYear_total());
					year_avg = total / day;
					year_avg = (double) Math.round(year_avg * 100) / 100;
					log.info("year_avg: {}", year_avg);
				}
				resultMap.put("pcoList", personalCountOffList);
				resultMap.put("statisticMap", statisticMap);
				resultMap.put("rankMap", rankMap);
				resultMap.put("year_avg", year_avg);
				return RestResponseHashMap.success("报数成功", resultMap);
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
