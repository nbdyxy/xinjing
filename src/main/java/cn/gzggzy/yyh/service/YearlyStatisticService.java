package cn.gzggzy.yyh.service;


import java.util.Date;

import cn.gzggzy.yyh.model.YearlyStatistic;

public interface YearlyStatisticService {
	
	YearlyStatistic saveOrUpdate(String uid);
	YearlyStatistic selectYearly(String uid, Date date);
}
