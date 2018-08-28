package cn.gzggzy.yyh.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.gzggzy.yyh.model.MonthlyStatistic;
import cn.gzggzy.yyh.model.YearlyStatistic;
import cn.gzggzy.yyh.service.MonthlyStatisticService;
import cn.gzggzy.yyh.service.PersonalStatisticService;
import cn.gzggzy.yyh.service.WeeklyStatisticService;
import cn.gzggzy.yyh.service.YearlyStatisticService;

@Service
public class PersonalStatisticServiceImpl implements PersonalStatisticService {
	
	private final static Logger log = LoggerFactory.getLogger(PersonalStatisticServiceImpl.class);
	
	@Autowired
	private WeeklyStatisticService weeklyStatisticService;
	
	@Autowired
	private MonthlyStatisticService monthlyStatisticService;
	
	@Autowired
	private YearlyStatisticService yearlyStatisticService;
	
	@Override
	public Map<String, Object> personalTotal(String uid, Date date, String dateStr) {
		log.info("uid: {}, dateStr: {}", uid, dateStr);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String, Object> ws_map = weeklyStatisticService.selectWeekly(uid, date);
		MonthlyStatistic ms = monthlyStatisticService.selectMonthly(uid, date);
		YearlyStatistic ys = yearlyStatisticService.selectYearly(uid, date);
		resultMap.put("ws_map", ws_map);
		resultMap.put("ms", ms);
		resultMap.put("ys", ys);
		return resultMap;
		
	}
	
}
