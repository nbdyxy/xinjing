package cn.gzggzy.yyh.service;


import java.util.Date;

import cn.gzggzy.yyh.model.YearlyStatistic;

public interface YearlyStatisticService {
	
	int saveOrUpdate(YearlyStatistic yearlyStatistic);
	
	YearlyStatistic selectYearly(String uid, Date date);
}
