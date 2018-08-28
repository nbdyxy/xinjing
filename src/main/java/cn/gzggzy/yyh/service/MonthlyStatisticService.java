package cn.gzggzy.yyh.service;


import java.util.Date;

import cn.gzggzy.yyh.model.MonthlyStatistic;

public interface MonthlyStatisticService {
	
	MonthlyStatistic saveOrUpdate(String uid);
	MonthlyStatistic selectMonthly(String uid, Date date);
}
