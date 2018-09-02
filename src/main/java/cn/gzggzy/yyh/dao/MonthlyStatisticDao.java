package cn.gzggzy.yyh.dao;

import org.apache.ibatis.annotations.Param;

import cn.gzggzy.yyh.model.MonthlyStatistic;

public interface MonthlyStatisticDao {
	
	int saveOrUpdate(MonthlyStatistic monthlyStatistic);
	
	MonthlyStatistic selectMonthly(@Param("uid") String uid, @Param("year") int year, @Param("month") int month);
}
