package cn.gzggzy.yyh.service;

import java.util.Date;
import java.util.List;

import cn.gzggzy.yyh.model.PersonalCountOff;

public interface PersonalCountOffService {
	
	int saveOrUpdate(PersonalCountOff personalCountOff);
	
	List<PersonalCountOff> selectTopFive(String uid);
	
	List<PersonalCountOff> selectAll(String uid, String randomId);
	
	List<PersonalCountOff> handleTopFive(String uid, String randomId);
	
	List<PersonalCountOff> updateTopFive(PersonalCountOff personalCountOff, String randomId, String uid, Date date);
	
	List<String> selectHistoryMonthIndex(String uid);
	
	List<PersonalCountOff> selectHistoryPerMonth(String uid, String date);
	
	int weeklyStatisticByUID(String uid, String beginDate, String endDate);
	
	/**
	 * @Title: selectOneDayTotal  
	 * @Description: TODO  
	 * @param date
	 * @return 某天报数的总数
	 * int
	 * @throws
	 */
	int selectOneDayTotal(String date);
	
}
