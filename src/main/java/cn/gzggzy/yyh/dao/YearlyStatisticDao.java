package cn.gzggzy.yyh.dao;

import org.apache.ibatis.annotations.Param;

import cn.gzggzy.yyh.model.YearlyStatistic;

public interface YearlyStatisticDao {
	
	YearlyStatistic selectYearly(@Param("uid") String uid, @Param("year") int year);
	
}
