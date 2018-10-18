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
	
	List<PersonalCountOff> selectTop100();
	
	/**
	 * @Title: selectPublicActivityCountNumber  
	 * @Description: 根据公共活动ID查询某公共活动的当前报数总量  
	 * @param public_activity_id
	 * @return
	 * int
	 * @throws
	 */
	int selectPublicActivityCountNumber(@Param("public_activity_id") String public_activity_id);
	
	/**
	 * @Title: selectPrivateActivityCountNumber  
	 * @Description: 根据私有活动ID查询某私有活动的当前报数总量   
	 * @param private_activity_id
	 * @return
	 * int
	 * @throws
	 */
	int selectPrivateActivityCountNumber(@Param("private_activity_id") String private_activity_id);
	
	/**
	 * @Title: selectActivityJoinNumber  
	 * @Description: 根据公共活动ID查询某公共活动的参与用户总量，参数为空时则查询所有参与过报数的用户
	 * @param public_activity_id
	 * @return
	 * int
	 * @throws
	 */
	int selectActivityJoinNumber(@Param("public_activity_id") String public_activity_id);
}
