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
import cn.gzggzy.yyh.util.DateUtil;

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
		String firstDate = DateUtil.toChar(personalCountOffList.get(0).getRecord_date(), "yyyy-MM-dd");
		String today = DateUtil.toChar(nowDate, "yyyy-MM-dd");
		//判断今天是否已经参与过报数
		if (!firstDate.equals(today)) {
			for (int i=4; i>=1; i--) {
				PersonalCountOff personalCountOff = personalCountOffList.get(i-1);
				personalCountOffList.set(i, personalCountOff);
			}
			String pid = UUID.randomUUID().toString();
			PersonalCountOff personalCountOff = new PersonalCountOff(pid, uid, nowDate, 0);
			personalCountOffList.set(0, personalCountOff);
		}
		return personalCountOffList;
		
	}
	
	@CachePut(cacheNames="count", key="'topFive'.concat(#p1)", unless="#result == null")
	public List<PersonalCountOff> updateTopFive(PersonalCountOff personalCountOff, String randomId, String uid) {
		List<PersonalCountOff> personalCountOffList = this.handleTopFive(uid, randomId);
		Date now = Calendar.getInstance().getTime();
		PersonalCountOff first = personalCountOffList.get(0);
		first.setRecord_number(personalCountOff.getRecord_number());
		first.setRecord_date(now);
		System.out.println(first);
		int flag = this.saveOrUpdate(first);
		System.out.println(flag);
		if (flag >= 1) {
			personalCountOffList.set(0, first);
			return personalCountOffList;
		}
		return null;
		
	}
	
}
