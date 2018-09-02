package cn.gzggzy.yyh.service;

import java.util.Date;
import java.util.Map;

import cn.gzggzy.yyh.model.PersonalCountOff;

/**
 * @author 余颜宏
 * @version 1.0
 * 统计服务类
 * */
public interface StatisticService {
	
	Map<String, Object> saveOrUpdateStatistic(PersonalCountOff pcoBefore, String randomId, String uid, Date date, String dateStr, int count);
	
}
