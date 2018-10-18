/**
 * Copyright 2018 Yanhong Yu
 * 
 * All right reserved.
 * 2018年10月13日
 * DynamicsServiceImpl.java
 * Yanhong Yu
 */
package cn.gzggzy.yyh.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.gzggzy.yyh.dao.MonthlyStatisticDao;
import cn.gzggzy.yyh.dao.PersonalCountOffDao;
import cn.gzggzy.yyh.dao.UserInfoDao;
import cn.gzggzy.yyh.dao.WeeklyStatisticDao;
import cn.gzggzy.yyh.dao.YearlyStatisticDao;
import cn.gzggzy.yyh.service.DynamicsService;
import cn.gzggzy.yyh.util.DateUtils;

/**
 * @author Administrator
 *
 */
@Service
public class DynamicsServiceImpl implements DynamicsService {
	
	@Autowired
	private UserInfoDao userInfoDao;
	
	@Autowired
	private PersonalCountOffDao personalCountOffDao;
	
	@Autowired
	private WeeklyStatisticDao weeklyStatisticDao;
	
	@Autowired
	private MonthlyStatisticDao monthlyStatisticDao;
	
	@Autowired
	private YearlyStatisticDao yearlyStatisticDao;
	
	/* 
	 * @see cn.gzggzy.yyh.service.DynamicsService#platformDynamics(java.lang.String)
	 */
	@Override
	public Map<String, Integer> platformDynamics(String date) {
		Date tarDate = DateUtils.parseStrToDate(date, "yyyy-MM-dd");
		int year = DateUtils.getYear(tarDate);
		int month = DateUtils.getMonth(tarDate);
		int year_week = DateUtils.getWeekthOfYear(tarDate);
		int month_week = DateUtils.getWeekthOfMonth(tarDate);
		int userNumber = userInfoDao.selectUserNumber(null);
		int attendUserNumber = personalCountOffDao.selectActivityJoinNumber(null);
		int dayStatistic = personalCountOffDao.selectOneDayTotal(date);
		int weekStatistic = weeklyStatisticDao.selectOneWeekTotal(year, month, year_week, month_week);
		int monthStatistic = monthlyStatisticDao.selectOneMonthTotal(year, month);
		int yearStatistic = yearlyStatisticDao.selectYearTotal(year);
		Map<String, Integer> result = new HashMap<String, Integer>();
		result.put("userNumber", userNumber);
		result.put("attendUserNumber", attendUserNumber);
		result.put("dayStatistic", dayStatistic);
		result.put("weekStatistic", weekStatistic);
		result.put("monthStatistic", monthStatistic);
		result.put("yearStatistic", yearStatistic);
		return result;
	}

}
