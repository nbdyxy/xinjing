package cn.gzggzy.yyh.dao;

import org.apache.ibatis.annotations.Param;

import cn.gzggzy.yyh.model.MonthlyStatistic;

public interface MonthlyStatisticDao {
	
	int saveOrUpdate(MonthlyStatistic monthlyStatistic);
	
	MonthlyStatistic selectMonthly(@Param("uid") String uid, @Param("year") int year, @Param("month") int month);
	
	/**
	 * @Title: selectOneMonthTotal  
	 * @Description: 查询某月的报数汇总  
	 * @param year 年份
	 * @param month 月份
	 * @return 该月总数
	 * int
	 * @throws
	 */
	int selectOneMonthTotal(@Param("year") int year, @Param("month") int month);
}
