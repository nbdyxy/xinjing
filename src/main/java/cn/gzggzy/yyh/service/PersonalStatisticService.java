package cn.gzggzy.yyh.service;

import java.util.Date;
import java.util.Map;

public interface PersonalStatisticService {
	
	Map<String, Object> personalTotal(String uid, String randomId, Date date, String dateStr);
	
}
