package cn.gzggzy.yyh.service.impl;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import cn.gzggzy.yyh.model.MonthlyStatistic;
import cn.gzggzy.yyh.model.PersonalCountOff;
import cn.gzggzy.yyh.model.WeeklyStatistic;
import cn.gzggzy.yyh.model.YearlyStatistic;
import cn.gzggzy.yyh.service.MonthlyStatisticService;
import cn.gzggzy.yyh.service.PersonalStatisticService;
import cn.gzggzy.yyh.service.StatisticService;
import cn.gzggzy.yyh.service.WeeklyStatisticService;
import cn.gzggzy.yyh.service.YearlyStatisticService;
import cn.gzggzy.yyh.util.DateUtils;

@Service
public class StatisticServiceImpl implements StatisticService {
	
	private static final Logger log = LoggerFactory.getLogger(StatisticServiceImpl.class);
	
	@Autowired
	private WeeklyStatisticService weeklyStatisticService;
	
	@Autowired
	private MonthlyStatisticService monthlyStatisticService;
	
	@Autowired
	private YearlyStatisticService yearlyStatisticService;
	
	@Autowired
	private PersonalStatisticService personalStatisticService;
	
	@Override
	@CachePut(cacheNames="statistic", key="#p1.concat(#p4)", unless="#result == null")
	public Map<String, Object> saveOrUpdateStatistic(PersonalCountOff pcoBefore, String randomId, String uid, Date date, String dateStr, int count) {
		//获取查询日期所在的年份、月份、周数，需判断是否存在跨月的情况
		int year = DateUtils.getYear(date);
		int month = DateUtils.getMonth(date);
		int year_week = DateUtils.getWeekthOfYear(date);
		int month_week = DateUtils.getWeekthOfMonth(date);
		//从缓存中获取当前日期年度、月度、周度汇总表的记录情况
		Map<String, Object> resultMap = personalStatisticService.personalTotal(uid, randomId, date, dateStr);
		//根据周度汇总表的记录情况进行处理
		int weekly_total =  (int) ((Map) resultMap.get("ws_map")).get("weekly_total");
		Object obj_ws = ( (Map) resultMap.get("ws_map")).get("ws");
		int before_record_number = pcoBefore.getRecord_number();
		WeeklyStatistic ws = new WeeklyStatistic();
		log.info("obj_ws: {}", obj_ws == null);
		if (null != obj_ws) {
			ws = (WeeklyStatistic) obj_ws;
			int week_total = ws.getWeek_total();
			int new_week_total = before_record_number == 0 ? count : (week_total+count-before_record_number);
			ws.setWeek_total(new_week_total);
		} else {
			ws = this.createWeeklyStatistic(uid, year, month, year_week, month_week, count);
		}
		weekly_total += count-before_record_number;
		log.info("ws: {}", ws);
		log.info("weekly_total: {}", weekly_total);
		weeklyStatisticService.saveOrUpdate(ws);
		((Map) resultMap.get("ws_map")).put("ws", ws);
		((Map) resultMap.get("ws_map")).put("weekly_total", weekly_total);
		//根据月度汇总表的记录情况进行处理
		MonthlyStatistic ms = (MonthlyStatistic) resultMap.get("ms");
		if (null != ms) {
			int month_total = ms.getMonth_total();
			int new_month_total = month_total + count - before_record_number;
			ms.setMonth_total(new_month_total);
		} else {
			ms = this.createMonthlyStatistic(uid, year, month, count);
		}
		log.info("ms: {}", ms);
		monthlyStatisticService.saveOrUpdate(ms);
		resultMap.put("ms", ms);
		//根据年度汇总表的记录情况进行处理
		YearlyStatistic ys = (YearlyStatistic) resultMap.get("ys");
		if (null != ys) {
			int year_total = ys.getYear_total();
			int new_year_total = year_total + count - before_record_number;
			ys.setYear_total(new_year_total);
		} else {
			ys = this.createYearlyStatistic(uid, year, count);
		}
		log.info("ys: {}", ys);
		yearlyStatisticService.saveOrUpdate(ys);
		resultMap.put("ys", ys);
		return resultMap;
	}
	
	private WeeklyStatistic createWeeklyStatistic(String uid, int year, int month, int year_week, int month_week, int statisticResult) {
		WeeklyStatistic ws = new WeeklyStatistic();
		String pid = UUID.randomUUID().toString();
		ws.setPid(pid);
		ws.setUid(uid);
		ws.setYear(year);
		ws.setMonth(month);
		ws.setYear_week(year_week);
		ws.setMonth_week(month_week);
		ws.setWeek_total(statisticResult);
		ws.setWeek_rank(1);
		ws.setIs_activity(1);
		return ws;
	}
	
	private MonthlyStatistic createMonthlyStatistic(String uid, int year, int month, int statisticResult) {
		MonthlyStatistic ms = new MonthlyStatistic();
		String pid = UUID.randomUUID().toString();
		ms.setPid(pid);
		ms.setUid(uid);
		ms.setYear(year);
		ms.setMonth(month);
		ms.setMonth_total(statisticResult);
		ms.setMonth_rank(1);
		ms.setIs_activity(1);
		return ms;
	}
	
	private YearlyStatistic createYearlyStatistic(String uid, int year, int statisticResult) {
		YearlyStatistic ys = new YearlyStatistic();
		String pid = UUID.randomUUID().toString();
		ys.setPid(pid);
		ys.setUid(uid);
		ys.setYear(year);
		ys.setYear_total(statisticResult);
		ys.setYear_rank(1);
		ys.setIs_activity(1);
		return ys;
	}
	
//	private WeeklyStatistic createWeeklyStatistic(String uid, Date date, int statisticResult) {
//		WeeklyStatistic ws = new WeeklyStatistic();
//		String pid = UUID.randomUUID().toString();
//		int year = DateUtils.getYear(date);
//		int month = DateUtils.getMonth(date);
//		int year_week = DateUtils.getWeekthOfYear(date);
//		int month_week =  DateUtils.getWeekthOfMonth(date);
//		ws.setPid(pid);
//		ws.setUid(uid);
//		ws.setYear(year);
//		ws.setMonth(month);
//		ws.setYear_week(year_week);
//		ws.setMonth_week(month_week);
//		ws.setWeek_total(statisticResult);
//		ws.setWeek_rank(1);
//		ws.setIs_activity(1);
//		return ws;
//	}

}
