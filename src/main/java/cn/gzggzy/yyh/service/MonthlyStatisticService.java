package cn.gzggzy.yyh.service;


import java.util.Date;

import cn.gzggzy.yyh.model.MonthlyStatistic;

public interface MonthlyStatisticService {
	
	int saveOrUpdate(MonthlyStatistic monthlyStatistic);
	MonthlyStatistic selectMonthly(String uid, Date date);
}
