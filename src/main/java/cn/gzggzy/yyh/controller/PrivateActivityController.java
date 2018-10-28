package cn.gzggzy.yyh.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.validation.Valid;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.gzggzy.yyh.config.Configuration;
import cn.gzggzy.yyh.filter.LoginFilter;
import cn.gzggzy.yyh.model.PersonalCountOff;
import cn.gzggzy.yyh.model.PrivateActivity;
import cn.gzggzy.yyh.model.RegisterUserInfo;
import cn.gzggzy.yyh.model.UserInfo;
import cn.gzggzy.yyh.model.YearlyStatistic;
import cn.gzggzy.yyh.response.bean.RestResponseHashMap;
import cn.gzggzy.yyh.service.PersonalCountOffService;
import cn.gzggzy.yyh.service.PersonalStatisticService;
import cn.gzggzy.yyh.service.PrivateActivityService;
import cn.gzggzy.yyh.service.RankService;
import cn.gzggzy.yyh.service.UserInfoService;
import cn.gzggzy.yyh.util.CookieUtil;
import cn.gzggzy.yyh.util.DESUtils;
import cn.gzggzy.yyh.util.DateUtils;
import cn.gzggzy.yyh.util.TokenUtil;

@Controller
@RequestMapping("/privateActivity")
public class PrivateActivityController {
	
	private static final Logger log = LoggerFactory.getLogger(PrivateActivityController.class);
	
	@Autowired
	private PrivateActivityService privateActivityService;
	
	@Autowired
	private TokenUtil tokenUtil;
	
	@Autowired
	private Configuration configuration;
	
	@Autowired
	public LoginFilter loginFilter;
	
	
	@PostMapping("/add")
	@ResponseBody
	public RestResponseHashMap add(PrivateActivity pa) {
		Map<String, Object> userInfoRedis = loginFilter.checkLogin();
		
		log.info(pa.getPrivate_activity_name());
		log.info(pa.getPrivate_activity_begin_time());
		log.info(pa.getPrivate_activity_end_time());
		log.info(pa.getPrivate_activity_target() + "--");
//		if (0 != userInfoRedis.size()) {
//			int result = privateActivityService.insertPrivateActivity(pa);
//			if (1 == result) {
//				return RestResponseHashMap.success("个人活动创建成功", true);
//			}
//		}
		return RestResponseHashMap.success("个人活动创建失败", true);
	}
	
}
