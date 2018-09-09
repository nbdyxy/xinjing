/**
 * Copyright 2018 Yanhong Yu
 * 
 * All right reserved.
 * 2018年9月9日
 * RankService.java
 * Yanhong Yu
 */
package cn.gzggzy.yyh.service;

import java.util.Date;
import java.util.Map;

public interface RankService {
	
	boolean rankAdd(String uid, Date date, String dateStr, String type, int countoff);
	
	Long getRank(String uid, String dateStr, String type, boolean reverse);
	
	Long remove(String uid, String dateStr, String type);
	
	Long size(Date date, String dateStr, String type);
	
	Map<String, Object> reverRankMapCountOff(String uid, Date date, int todayCountOff, Map<String, Object> map);
	
}
