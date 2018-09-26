package cn.gzggzy.yyh.dao;

import org.apache.ibatis.annotations.Param;

import cn.gzggzy.yyh.model.YearlyStatistic;

public interface YearlyStatisticDao {
	
	int saveOrUpdate(YearlyStatistic yearlyStatistic);
	
	YearlyStatistic selectYearly(@Param("uid") String uid, @Param("year") int year);
	
	/**
	 * @Title: selectYearTotal  
	 * @Description: 查询某年的报数汇总  
	 * @param year
	 * @return 该年总数
	 * int
	 * @throws
	 */
	int selectYearTotal(@Param("year") int year);
}
