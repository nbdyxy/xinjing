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
	
	/**
	 * @Title: selectTop100  
	 * @Description: 查询最新的前100报数  
	 * @return
	 * List<PersonalCountOff>
	 * @throws
	 */
	List<PersonalCountOff> selectTop100();
	
	/**
	 * @Title: selectPublicActivityCountNumber  
	 * @Description: 根据公共活动ID查询某公共活动的当前报数总量  
	 * @param public_activity_id
	 * @return
	 * int
	 * @throws
	 */
	int selectPublicActivityCountNumber(String public_activity_id);
	
	/**
	 * @Title: selectPrivateActivityCountNumber  
	 * @Description: 根据私有活动ID查询某私有活动的当前报数总量   
	 * @param private_activity_id
	 * @return
	 * int
	 * @throws
	 */
	int selectPrivateActivityCountNumber(String private_activity_id);
	
	/**
	 * @Title: selectActivityJoinNumber  
	 * @Description: 根据公共活动ID查询某公共活动的参与用户总量，参数为空时则查询所有参与过报数的用户
	 * @param public_activity_id
	 * @return
	 * int
	 * @throws
	 */
	int selectActivityJoinNumber(String public_activity_id);
	
}
