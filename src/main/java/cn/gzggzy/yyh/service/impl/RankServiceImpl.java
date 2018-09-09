/**
 * Copyright 2018 Yanhong Yu
 * 
 * All right reserved.
 * 2018年9月9日
 * RankServiceImpl.java
 * Yanhong Yu
 */
package cn.gzggzy.yyh.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import cn.gzggzy.yyh.model.MonthlyStatistic;
import cn.gzggzy.yyh.model.YearlyStatistic;
import cn.gzggzy.yyh.service.RankService;
import cn.gzggzy.yyh.util.DateUtils;

@Service
public class RankServiceImpl implements RankService {
	
	private final static Logger log = LoggerFactory.getLogger(RankServiceImpl.class); 
	
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	
	@Resource(name="redisTemplate")
	private ZSetOperations<String, String> zsetOps;
	
	@Override
	public boolean rankAdd(String uid, Date date, String dateStr, String type, int countoff) {
		log.info("key: {}, value: {}, score: {}", type + dateStr, uid, countoff);
		return zsetOps.add(type + dateStr, uid, countoff) ? redisTemplate.expireAt(type + dateStr, DateUtils.getDayEndTime(date)) : false;
	}

	@Override
	public Long getRank(String uid, String dateStr, String type, boolean reverse) {
		log.info("getRank---key: {}, value: {}", type + dateStr, uid);
		if (reverse) {
			return zsetOps.reverseRank(type + dateStr, uid) + 1;
		}
		return zsetOps.rank(type + dateStr, uid) + 1;
	}

	@Override
	public Long remove(String uid, String dateStr, String type) {
		return zsetOps.remove(type + dateStr, uid);
	}

	@Override
	public Long size(Date date, String dateStr, String type) {
		return zsetOps.size(type + dateStr);
	}

	@Override
	public Map<String, Object> reverRankMapCountOff(String uid, Date date, int todayCountOff, Map<String, Object> map) {
		Map<String, Object> rank_map = new HashMap<String, Object>();
		Long daily_rank, week_rank, month_rank, year_rank;
		daily_rank = -1l;
		week_rank = -1l;
		month_rank = -1l;
		year_rank = -1l;
		//当日报数的排名缓存处理
		String dateStr = DateUtils.parseDateToStr(date, "yyMMdd");
		if (todayCountOff > 0) {
			rankAdd(uid, date, dateStr, "daily", todayCountOff);
			daily_rank = getRank(uid, dateStr, "daily", true);
			rank_map.put("daily_rank", daily_rank);
		} else {
			rank_map.put("daily_rank", "-");
		}
		//周度报数的排名缓存处理
		Date endOfWeek = DateUtils.getLastDayOfWeek(date);
		String endOfWeekStr = DateUtils.parseDateToStr(endOfWeek, "yyMMdd");
		int week_total = (int) ((Map<String, Object>) map.get("ws_map")).get("weekly_total");
		if (week_total > 0) {
			rankAdd(uid, endOfWeek, endOfWeekStr, "weekly", week_total);
			week_rank = getRank(uid, endOfWeekStr, "weekly", true);
			rank_map.put("week_rank", week_rank);
		} else {
			rank_map.put("week_rank", "-");
		}
		
		//月度报数的排名缓存处理
		Date endOfMonth = DateUtils.getLastDayOfMonth(date); 
		String endOfMonthStr =  DateUtils.parseDateToStr(endOfMonth, "yyMMdd");
		MonthlyStatistic ms = (MonthlyStatistic) map.get("ms");
		if (null != ms) {
			rankAdd(uid, endOfMonth, endOfMonthStr, "monthly", ms.getMonth_total());
			month_rank = getRank(uid, endOfMonthStr, "monthly", true);
			rank_map.put("month_rank", month_rank);
		} else {
			rank_map.put("month_rank", "-");
		}
		
		//年度报数的排名缓存处理
		Date endOfYear = DateUtils.getLastDayOfYear(date); 
		String endOfYearStr = DateUtils.parseDateToStr(endOfYear, "yyMMdd");
		YearlyStatistic ys = (YearlyStatistic) map.get("ys");
		if (null != ys) {
			rankAdd(uid, endOfYear, endOfYearStr, "yearly", ys.getYear_total());
			year_rank = getRank(uid, endOfYearStr, "yearly", true);
			rank_map.put("year_rank", year_rank);
		} else {
			rank_map.put("year_rank", "-");
		}
		
		return rank_map;
	}
	
}
