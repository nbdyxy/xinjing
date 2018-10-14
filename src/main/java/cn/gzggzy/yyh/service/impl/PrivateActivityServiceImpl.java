/**
 * Copyright 2018 Yanhong Yu
 * 
 * All right reserved.
 * 2018年10月14日
 * PrivateActivityServiceImpl.java
 * Yanhong Yu
 */
package cn.gzggzy.yyh.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.gzggzy.yyh.dao.PrivateActivityDao;
import cn.gzggzy.yyh.model.PrivateActivity;
import cn.gzggzy.yyh.service.PrivateActivityService;

/**
 * @author Administrator
 *
 */
@Service
public class PrivateActivityServiceImpl implements PrivateActivityService {
	
	@Autowired
	private PrivateActivityDao privateActivityDao;
	
	/* 
	 * @see cn.gzggzy.yyh.service.PrivateActivityService#selectActivityEnable()
	 */
	@Override
	public List<PrivateActivity> selectActivityEnable() {
		return privateActivityDao.selectActivityEnable();
	}

	/* 
	 * @see cn.gzggzy.yyh.service.PrivateActivityService#selectActivityById(java.lang.String)
	 */
	@Override
	public PrivateActivity selectActivityById(String private_activity_id) {
		return privateActivityDao.selectActivityById(private_activity_id);
	}

	/* 
	 * @see cn.gzggzy.yyh.service.PrivateActivityService#insertPrivateActivity(cn.gzggzy.yyh.model.PrivateActivity)
	 */
	@Override
	public int insertPrivateActivity(PrivateActivity pa) {
		return privateActivityDao.insertPrivateActivity(pa);
	}

	/* 
	 * @see cn.gzggzy.yyh.service.PrivateActivityService#updatePrivateActivity(cn.gzggzy.yyh.model.PrivateActivity)
	 */
	@Override
	public int updatePrivateActivity(PrivateActivity pa) {
		return privateActivityDao.updatePrivateActivity(pa);
	}

}
