package cn.gzggzy.yyh.dao;

import org.apache.ibatis.annotations.Param;

import cn.gzggzy.yyh.model.WeeklyStatistic;

public interface WeeklyStatisticDao {
	
	int saveOrUpdate(WeeklyStatistic weeklyStatistic);
	
	WeeklyStatistic selectWeekly(@Param("uid") String uid,@Param("year") int year,@Param("month") int month,@Param("year_week") int year_week);
	
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
	int selectOneWeekTotal(@Param("year") int year, @Param("month") int month, @Param("year_week") int year_week, @Param("month_week") int month_week);
}
