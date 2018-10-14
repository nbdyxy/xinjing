/**
 * Copyright 2018 Yanhong Yu
 * 
 * All right reserved.
 * 2018年10月14日
 * PublicActivityService.java
 * Yanhong Yu
 */
package cn.gzggzy.yyh.service;

import java.util.List;

import cn.gzggzy.yyh.model.PublicActivity;

/**
 * @author Administrator
 *
 */
public interface PublicActivityService {
	
	/**
	 * @Title: selectEnable  
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
	PublicActivity selectActivityById(String public_activity_id);
	
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
	
}
