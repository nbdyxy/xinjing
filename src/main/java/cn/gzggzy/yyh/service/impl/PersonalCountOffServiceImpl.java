package cn.gzggzy.yyh.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import cn.gzggzy.yyh.dao.PersonalCountOffDao;
import cn.gzggzy.yyh.model.PersonalCountOff;
import cn.gzggzy.yyh.service.PersonalCountOffService;
import cn.gzggzy.yyh.util.DateUtils;

@Service
public class PersonalCountOffServiceImpl implements PersonalCountOffService {

	@Autowired
	private PersonalCountOffDao personalCountOffDao;
	
	@Override
	public int saveOrUpdate(PersonalCountOff personalCountOff) {
		return personalCountOffDao.saveOrUpdate(personalCountOff);
	}

	@Override
	public List<PersonalCountOff> selectTopFive(String uid) {
		return personalCountOffDao.selectTopFive(uid);
	}
	
	@Override
	@Cacheable(value="count", key="'all'.concat(#p1)", unless="#result == null")
	public List<PersonalCountOff> selectAll(String uid, String randomId) {
		return personalCountOffDao.selectAll(uid);
	}

	@Override
	@Cacheable(value="count", key="'topFive'.concat(#p1)")
	public List<PersonalCountOff> handleTopFive(String uid, String randomId) {
		List<PersonalCountOff> personalCountOffList = this.selectTopFive(uid);
		Date nowDate = Calendar.getInstance().getTime();
		int size = personalCountOffList.size();
		String pid = UUID.randomUUID().toString();
		PersonalCountOff tempFirst = new PersonalCountOff(pid, uid, nowDate, 0);
		
		if (0 != size) {
			String firstDate = DateUtils.parseDateToStr(personalCountOffList.get(0).getRecord_date(), "yyyy-MM-dd");
			String today = DateUtils.parseDateToStr(nowDate, "yyyy-MM-dd");
			//判断今天是否已经参与过报数
			if (!firstDate.equals(today)) {
				personalCountOffList.add(0, tempFirst);
				if (5 < personalCountOffList.size()) {
					personalCountOffList.remove(5);
				}
			}
		} else {
			personalCountOffList.add(0, tempFirst);
		}
		return personalCountOffList;
		
	}
	
	@CachePut(cacheNames="count", key="'topFive'.concat(#p1)", unless="#result == null")
	public List<PersonalCountOff> updateTopFive(PersonalCountOff personalCountOff, String randomId, String uid, Date date) {
		List<PersonalCountOff> personalCountOffList = this.handleTopFive(uid, randomId);
		PersonalCountOff first = personalCountOffList.get(0);
		first.setRecord_number(personalCountOff.getRecord_number());
		first.setRecord_date(date);
		int flag = this.saveOrUpdate(first);
		if (flag >= 1) {
			personalCountOffList.set(0, first);
			return personalCountOffList;
		}
		return null;
		
	}

	@Override
	public List<String> selectHistoryMonthIndex(String uid) {
		return personalCountOffDao.selectHistoryMonthIndex(uid);
	}

	@Override
	public List<PersonalCountOff> selectHistoryPerMonth(String uid, String date) {
		return personalCountOffDao.selectHistoryPerMonth(uid, date);
	}

	@Override
	public int weeklyStatisticByUID(String uid, String beginDate, String endDate) {
		return personalCountOffDao.weeklyStatisticByUID(uid, beginDate, endDate);
	}

}
