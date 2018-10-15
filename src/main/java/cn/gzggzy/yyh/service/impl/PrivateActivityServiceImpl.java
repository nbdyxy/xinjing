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

import com.github.pagehelper.PageHelper;

import cn.gzggzy.yyh.dao.PrivateActivityDao;
import cn.gzggzy.yyh.model.PrivateActivity;
import cn.gzggzy.yyh.model.PublicActivity;
import cn.gzggzy.yyh.service.PrivateActivityService;

/**
 * @author Administrator
 *
 */
@Service
public class PrivateActivityServiceImpl implements PrivateActivityService {
	
	@Autowired
	private PrivateActivityDao privateActivityDao;
	
	@Override
	public List<PrivateActivity> selectActivityAll(int pageNum, int pageSize, String orderBy, boolean page) {
		if (page) {
			PageHelper.startPage(pageNum, pageSize, orderBy);
		}
		return privateActivityDao.selectActivityAll();
	}
	
	/* 
	 * @see cn.gzggzy.yyh.service.PrivateActivityService#selectActivityEnable()
	 */
	@Override
	public List<PrivateActivity> selectActivityEnable(int pageNum, int pageSize, String orderBy, boolean page) {
		if (page) {
			PageHelper.startPage(pageNum, pageSize, orderBy);
		}
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

	/* 
	 * @see cn.gzggzy.yyh.service.PrivateActivityService#deletePrivateActivityById(java.util.List)
	 */
	@Override
	public int deletePrivateActivityById(List<String> pidList) {
		return privateActivityDao.deletePrivateActivityById(pidList);
	}

}
