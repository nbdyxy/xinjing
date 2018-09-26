package cn.gzggzy.yyh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.gzggzy.yyh.model.PersonalCountOff;

public interface PersonalCountOffDao {
	
	int saveOrUpdate(PersonalCountOff personalCountOff);
	
	List<PersonalCountOff> selectTopFive(@Param("uid") String uid);
	
	List<PersonalCountOff> selectAll(@Param("uid") String uid);
	
	List<String> selectHistoryMonthIndex(@Param("uid") String uid);
	
	List<PersonalCountOff> selectHistoryPerMonth(@Param("uid") String uid, @Param("date") String date);
	
	int weeklyStatisticByUID(@Param("uid") String uid, @Param("beginDate") String beginDate, @Param("endDate") String endDate);
	
	int selectOneDayTotal(@Param("date") String date);
}
