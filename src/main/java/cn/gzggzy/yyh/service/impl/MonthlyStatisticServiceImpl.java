package cn.gzggzy.yyh.service.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.gzggzy.yyh.dao.MonthlyStatisticDao;
import cn.gzggzy.yyh.dao.PersonalCountOffDao;
import cn.gzggzy.yyh.model.MonthlyStatistic;
import cn.gzggzy.yyh.service.MonthlyStatisticService;
import cn.gzggzy.yyh.util.DateUtils;

@Service
public class MonthlyStatisticServiceImpl implements MonthlyStatisticService {
	
	private final static Logger log = LoggerFactory.getLogger(MonthlyStatisticServiceImpl.class);
	
	@Autowired
	private MonthlyStatisticDao monthlyStatisticDao;
	
	@Override
	public int saveOrUpdate(MonthlyStatistic monthlyStatistic) {
		return monthlyStatisticDao.saveOrUpdate(monthlyStatistic);
	}
	
	@Override
	public MonthlyStatistic selectMonthly(String uid, Date date) {
		if (null == date) {
			date = new Date();
		}
		log.info("date: {}", DateUtils.parseDateToStr(date, "yyyy-MM-dd"));
		int year = DateUtils.getYear(date);
		int month = DateUtils.getMonth(date);
		log.info("uid: {}, year: {}, month: {}", uid, year, month);
		return monthlyStatisticDao.selectMonthly(uid, year, month);
	}
	
	
	
}
