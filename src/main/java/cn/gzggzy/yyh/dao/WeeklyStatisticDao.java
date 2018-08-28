package cn.gzggzy.yyh.dao;

import org.apache.ibatis.annotations.Param;

import cn.gzggzy.yyh.model.WeeklyStatistic;

public interface WeeklyStatisticDao {
	
	int saveOrUpdate(WeeklyStatistic weeklyStatistic);
	
	WeeklyStatistic selectWeekly(@Param("uid") String uid,@Param("year") int year,@Param("month") int month,@Param("year_week") int year_week);
}
