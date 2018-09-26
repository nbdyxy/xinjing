package cn.gzggzy.yyh.service;


import java.util.Date;

import cn.gzggzy.yyh.model.YearlyStatistic;

public interface YearlyStatisticService {
	
	int saveOrUpdate(YearlyStatistic yearlyStatistic);
	
	YearlyStatistic selectYearly(String uid, Date date);
	
	/**
	 * @Title: selectYearTotal  
	 * @Description: 查询某年的报数汇总  
	 * @param year
	 * @return 该年总数
	 * int
	 * @throws
	 */
	int selectYearTotal(int year);
}
