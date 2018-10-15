/**
 * Copyright 2018 Yanhong Yu
 * 
 * All right reserved.
 * 2018年10月14日
 * PublicActivityServiceImpl.java
 * Yanhong Yu
 */
package cn.gzggzy.yyh.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;

import cn.gzggzy.yyh.dao.PublicActivityDao;
import cn.gzggzy.yyh.model.PublicActivity;
import cn.gzggzy.yyh.service.PublicActivityService;

/**
 * @author Administrator
 *
 */
@Service
public class PublicActivityServiceImpl implements PublicActivityService {
	
	@Autowired
	public PublicActivityDao publicActivityDao;
	
	/* 
	 * @see cn.gzggzy.yyh.service.PublicActivityService#selectActivityAll(int, int, java.lang.String, boolean)
	 */
	@Override
	public List<PublicActivity> selectActivityAll(int pageNum, int pageSize, String orderBy, boolean page) {
		if (page) {
			PageHelper.startPage(pageNum, pageSize, orderBy);
		}
		return publicActivityDao.selectActivityAll();
	}
	
	/* 
	 * @see cn.gzggzy.yyh.service.PublicActivityService#selectEnable()
	 */
	@Override
	public List<PublicActivity> selectActivityEnable(int pageNum, int pageSize, String orderBy, boolean page) {
		if (page) {
			PageHelper.startPage(pageNum, pageSize, orderBy);
		}
		return publicActivityDao.selectActivityEnable();
	}

	/* 
	 * @see cn.gzggzy.yyh.service.PublicActivityService#selectActivityById(java.lang.String)
	 */
	@Override
	public PublicActivity selectActivityById(String public_activity_id) {
		return publicActivityDao.selectActivityById(public_activity_id);
	}

	/* 
	 * @see cn.gzggzy.yyh.service.PublicActivityService#insertPublicActivity(cn.gzggzy.yyh.model.PublicActivity)
	 */
	@Override
	public int insertPublicActivity(PublicActivity pa) {
		return publicActivityDao.insertPublicActivity(pa);
	}

	/* 
	 * @see cn.gzggzy.yyh.service.PublicActivityService#updatePublicActivity(cn.gzggzy.yyh.model.PublicActivity)
	 */
	@Override
	public int updatePublicActivity(PublicActivity pa) {
		return publicActivityDao.updatePublicActivity(pa);
	}

	/* 
	 * @see cn.gzggzy.yyh.service.PublicActivityService#deletePublicActivityById(java.util.List)
	 */
	@Override
	public int deletePublicActivityById(List<String> pidList) {
		return publicActivityDao.deletePublicActivityById(pidList);
	}

}
