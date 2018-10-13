/**
 * Copyright 2018 Yanhong Yu
 * 
 * All right reserved.
 * 2018年10月13日
 * DynamicsService.java
 * Yanhong Yu
 */
package cn.gzggzy.yyh.service;

import java.util.Map;

/**
 * @author Administrator
 *
 */
public interface DynamicsService {

	/**
	 * 
	 * @Title: platformDynamics  
	 * @Description: 查询平台总动态  
	 * @param date
	 * @return
	 * Map<String,Integer>
	 * @throws
	 */
	Map<String, Integer> platformDynamics(String date);
	
}
