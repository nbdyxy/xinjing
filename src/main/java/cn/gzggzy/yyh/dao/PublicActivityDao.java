/**
 * Copyright 2018 Yanhong Yu
 * 
 * All right reserved.
 * 2018年10月14日
 * PublicActivityDao.java
 * Yanhong Yu
 */
package cn.gzggzy.yyh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.gzggzy.yyh.model.PublicActivity;

/**
 * @author Administrator
 *
 */
public interface PublicActivityDao {
	
	/**
	 * @Title: selectActivityAll  
	 * @Description: 查询所有的公共活动  
	 * @return
	 * List<PublicActivity>
	 * @throws
	 */
	List<PublicActivity> selectActivityAll();
	
	/**
	 * @Title: selectActivityEnable  
	 * @Description: 查询当前可用的公共活动  
	 * @return
	 * List<PublicActivity>
	 * @throws
	 */
	List<PublicActivity> selectActivityEnable();
	
	/**
	 * @Title: selectActivityById  
	 * @Description: 根据活动id查询公共活动  
	 * @param public_activity_id
	 * @return
	 * PublicActivity
	 * @throws
	 */
	PublicActivity selectActivityById(@Param("public_activity_id") String public_activity_id);
	
	/**
	 * @Title: insertPublicActivity  
	 * @Description: 新增公共活动  
	 * @param pa
	 * @return
	 * int
	 * @throws
	 */
	int insertPublicActivity(PublicActivity pa);
	
	/**
	 * @Title: updatePublicActivity  
	 * @Description: 修改公共活动  
	 * @param pa
	 * @return
	 * int
	 * @throws
	 */
	int updatePublicActivity(PublicActivity pa);
	
	
	/**
	 * @Title: deletePublicActivityById  
	 * @Description: 删除集合内指定主键的公共活动
	 * @param pidList
	 * @return
	 * int
	 * @throws
	 */
	int deletePublicActivityById(@Param("list") List<String> pidList);
	
	/**
	 * @Title: selectAll  
	 * @Description: 查询所有公共活动
	 * @param pidList
	 * @return
	 * int
	 * @throws
	 */
	List<PublicActivity> selectAll();
}
