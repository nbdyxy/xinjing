package cn.gzggzy.yyh.service;


import java.util.Date;

import cn.gzggzy.yyh.model.MonthlyStatistic;

public interface MonthlyStatisticService {
	
	int saveOrUpdate(MonthlyStatistic monthlyStatistic);
	MonthlyStatistic selectMonthly(String uid, Date date);
	/**
	 * @Title: selectOneMonthTotal  
	 * @Description: 查询某月的报数汇总  
	 * @param year 年份
	 * @param month 月份
	 * @return 该月总数
	 * int
	 * @throws
	 */
	int selectOneMonthTotal(int year, int month);
}
