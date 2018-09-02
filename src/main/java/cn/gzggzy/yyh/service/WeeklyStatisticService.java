package cn.gzggzy.yyh.service;


import java.util.Date;
import java.util.Map;

import cn.gzggzy.yyh.model.WeeklyStatistic;

public interface WeeklyStatisticService {
	
	int saveOrUpdate(WeeklyStatistic ws);
	
	Map<String, Object> selectWeekly(String uid, Date date);
}
