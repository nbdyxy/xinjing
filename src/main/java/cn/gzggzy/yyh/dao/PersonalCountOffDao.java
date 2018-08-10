package cn.gzggzy.yyh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.gzggzy.yyh.model.PersonalCountOff;

public interface PersonalCountOffDao {
	
	int saveOrUpdate(PersonalCountOff personalCountOff);
	
	List<PersonalCountOff> selectTopFive(@Param("uid") String uid);
	
	List<PersonalCountOff> selectAll(@Param("uid") String uid);
}
