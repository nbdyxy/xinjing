package cn.gzggzy.yyh.service.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.gzggzy.yyh.dao.MonthlyStatisticDao;
import cn.gzggzy.yyh.dao.PersonalCountOffDao;
import cn.gzggzy.yyh.model.MonthlyStatistic;
import cn.gzggzy.yyh.service.MonthlyStatisticService;
import cn.gzggzy.yyh.util.DateUtils;

@Service
public class MonthlyStatisticServiceImpl implements MonthlyStatisticService {
	
	private final static Logger log = LoggerFactory.getLogger(MonthlyStatisticServiceImpl.class);
	
	@Autowired
	private MonthlyStatisticDao monthlyStatisticDao;
	
	@Override
	public MonthlyStatistic saveOrUpdate(String uid) {
//		Calendar c = Calendar.getInstance();
//		Date beginDate = DateUtils.getFirstDayOfWeek(c.getTime());
//		Date endDate = DateUtils.getLastDayOfWeek(c.getTime());
//		String beginDateStr = DateUtils.parseDateToStr(beginDate, "yyyy-MM-dd");
//		String endDateStr = DateUtils.parseDateToStr(endDate, "yyyy-MM-dd");
//		//判断起止日期是否在同一个月
//		int beginMonth = DateUtils.getMonth(beginDate);
//		int endMonth = DateUtils.getMonth(endDate);
//		int statisticResult = 0;
//		if (beginMonth == endMonth) {
//			try {
//				statisticResult += personalCountOffDao.weeklyStatisticByUID(uid, beginDateStr, endDateStr);
//			} catch (Exception e) {
//				log.info("未查询到结果!");
//			}
//			Integer year = DateUtils.getYear(c.getTime());
//			Integer month = DateUtils.getMonth(c.getTime());
//			Integer year_week = DateUtils.getWeekthOfYear(c.getTime());
//			Integer month_week = c.get(Calendar.WEEK_OF_MONTH);
//			log.info("beginDate: {}, endDate: {}", beginDate, endDate);
//			log.info("year: {}, month: {}, year_week: {}, month_week: {}", year, month, year_week, month_week);
//			WeeklyStatistic ws = this.createWeeklyStatistic(uid, beginDate, statisticResult);
//			int result = weeklyStatisticDao.saveOrUpdate(ws);
//			if (1 == result) {
//				return ws;
//			}
//		} else {
//			int beginYear = DateUtils.getYear(beginDate);
//			int endYear = DateUtils.getYear(endDate);
//			Date beginDateMid = DateUtils.getFirstDayOfMonth(beginYear, beginMonth);
//			Date endDateMid = DateUtils.getFirstDayOfMonth(endYear, endMonth);
//			String beginDateMidStr = DateUtils.parseDateToStr(beginDateMid, "yyyy-MM-dd");
//			String endDateMidStr = DateUtils.parseDateToStr(endDateMid, "yyyy-MM-dd");
//			statisticResult += personalCountOffDao.weeklyStatisticByUID(uid, beginDateStr, beginDateMidStr);
//			WeeklyStatistic ws = this.createWeeklyStatistic(uid, beginDate, statisticResult);
//			int result = weeklyStatisticDao.saveOrUpdate(ws);
//			statisticResult += personalCountOffDao.weeklyStatisticByUID(uid, endDateMidStr, endDateStr);
//			ws = this.createWeeklyStatistic(uid, endDate, statisticResult);
//			result = weeklyStatisticDao.saveOrUpdate(ws);
//			if (1 == result) {
//				return ws;
//			}
//		}
		return null;
	}
	
	@Override
	public MonthlyStatistic selectMonthly(String uid, Date date) {
		if (null == date) {
			date = new Date();
		}
		log.info("date: {}", DateUtils.parseDateToStr(date, "yyyy-MM-dd"));
		int year = DateUtils.getYear(date);
		int month = DateUtils.getMonth(date);
		log.info("uid: {}, year: {}, month: {}", uid, year, month);
		return monthlyStatisticDao.selectMonthly(uid, year, month);
//		return null;
	}
	
	
	
}
