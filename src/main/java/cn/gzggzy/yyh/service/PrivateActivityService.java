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

import cn.gzggzy.yyh.model.PrivateActivity;

/**
 * @author Administrator
 *
 */
public interface PrivateActivityService {
	
	/**
	 * @Title: selectActivityAll  
	 * @Description: 查询所有的私有活动  
	 * @param pageNum 页码
	 * @param pageSize 每页数量
	 * @param page 是否使用分页
	 * @return
	 * List<PrivateActivity>
	 * @throws
	 */
	List<PrivateActivity> selectActivityAll(int pageNum, int pageSize, String orderBy, boolean page);
	
	/**
	 * @Title: selectActivityEnable  
	 * @Description: 查询当前可用的私有活动  
	 * @param pageNum 页码
	 * @param pageSize 每页数量
	 * @param page 是否使用分页
	 * @return
	 * List<PrivateActivity>
	 * @throws
	 */
	List<PrivateActivity> selectActivityEnable(int pageNum, int pageSize, String orderBy, boolean page, String uid);
	
	/**
	 * @Title: selectActivityById  
	 * @Description: 根据活动id查询私有活动  
	 * @param private_activity_id
	 * @return
	 * PrivateActivity
	 * @throws
	 */
	PrivateActivity selectActivityById(String private_activity_id);
	
	/**
	 * @Title: insertPrivateActivity  
	 * @Description: 新增私有活动  
	 * @param pa
	 * @return
	 * int
	 * @throws
	 */
	int insertPrivateActivity(PrivateActivity pa);
	
	/**
	 * @Title: updatePrivateActivity  
	 * @Description: 修改私有活动  
	 * @param pa
	 * @return
	 * int
	 * @throws
	 */
	int updatePrivateActivity(PrivateActivity pa);
	
	/**
	 * @Title: deletePrivateActivityById  
	 * @Description: 删除集合内指定主键的私有活动
	 * @param pidList
	 * @return
	 * int
	 * @throws
	 */
	int deletePrivateActivityById(List<String> pidList);
	
}
