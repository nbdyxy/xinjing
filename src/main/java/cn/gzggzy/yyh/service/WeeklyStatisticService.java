package cn.gzggzy.yyh.service;


import java.util.Date;
import java.util.Map;

import cn.gzggzy.yyh.model.WeeklyStatistic;

public interface WeeklyStatisticService {
	
	int saveOrUpdate(WeeklyStatistic ws);
	
	Map<String, Object> selectWeekly(String uid, Date date);
	
	/**
	 * 
	 * @Title: selectOneWeekTotal  
	 * @Description: 查询某月的报数汇总  
	 * @param year
	 * @param month
	 * @param year_week
	 * @param month_week
	 * @return 该周总数
	 * int
	 * @throws
	 */
	int selectOneWeekTotal(int year, int month, int year_week, int month_week);
}
