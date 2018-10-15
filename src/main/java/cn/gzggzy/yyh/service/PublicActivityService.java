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
	 * @Title: selectActivityAll  
	 * @Description: 查询所有的公共活动  
	 * @param pageNum 页码
	 * @param pageSize 每页数量
	 * @param page 是否使用分页
	 * @return
	 * List<PublicActivity>
	 * @throws
	 */
	List<PublicActivity> selectActivityAll(int pageNum, int pageSize, String orderBy, boolean page);
	
	/**
	 * @Title: selectActivityEnable  
	 * @Description: 查询当前可用的公共活动  
	 * @param pageNum 页码
	 * @param pageSize 每页数量
	 * @param page 是否使用分页
	 * @return
	 * List<PublicActivity>
	 * @throws
	 */
	List<PublicActivity> selectActivityEnable(int pageNum, int pageSize, String orderBy, boolean page);
	
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
	
	/**
	 * @Title: deletePublicActivityById  
	 * @Description: 删除集合内指定主键的公共活动
	 * @param pidList
	 * @return
	 * int
	 * @throws
	 */
	int deletePublicActivityById(List<String> pidList);
}
