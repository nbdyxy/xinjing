package cn.gzggzy.yyh.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.gzggzy.yyh.dao.WeeklyStatisticDao;
import cn.gzggzy.yyh.model.WeeklyStatistic;
import cn.gzggzy.yyh.service.WeeklyStatisticService;
import cn.gzggzy.yyh.util.DateUtils;

@Service
public class WeeklyStatisticServiceImpl implements WeeklyStatisticService {
	
	private final static Logger log = LoggerFactory.getLogger(WeeklyStatisticServiceImpl.class);
	
	@Autowired
	private WeeklyStatisticDao weeklyStatisticDao;
	
	@Override
	public int saveOrUpdate(WeeklyStatistic ws) {
		return weeklyStatisticDao.saveOrUpdate(ws);
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
//		return null;
	}
	
	@Override
	public Map<String, Object> selectWeekly(String uid, Date date) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (null == date) {
			date = new Date();
		}
		//判断同一周是否跨月
		Date beginDate = DateUtils.getFirstDayOfWeek(date);
		int begin_year = DateUtils.getYear(beginDate);
		int begin_month = DateUtils.getMonth(beginDate);
		int begin_year_week = DateUtils.getWeekthOfYear(beginDate);
		int now_year = DateUtils.getYear(date);
		int now_month = DateUtils.getMonth(date);
		int now_year_week = DateUtils.getWeekthOfYear(date);
		WeeklyStatistic ws = weeklyStatisticDao.selectWeekly(uid, now_year, now_month, now_year_week);
		log.info("ws: {}", ws);
		int week_total = 0;
		if (null != ws) {
			week_total = ws.getWeek_total();
			
		}
		if (begin_month != now_month) {
			WeeklyStatistic preWs =  weeklyStatisticDao.selectWeekly(uid, begin_year, begin_month, begin_year_week);
			if (null != preWs) {
				week_total += preWs.getWeek_total();
			}
			
		}
		map.put("weekly_total", week_total);
		map.put("ws", ws);
		log.info("uid: {}, year: {}, month: {}, year_week: {}", uid, now_year, now_month, now_year_week);
		return map;
	}
	
	
	
}
