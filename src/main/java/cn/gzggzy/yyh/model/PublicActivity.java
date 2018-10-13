/**
 * Copyright 2018 Yanhong Yu
 * 
 * All right reserved.
 * 2018年10月13日
 * PublicActivity.java
 * Yanhong Yu
 */
package cn.gzggzy.yyh.model;

import java.util.Date;

/**
 * @author Administrator
 *
 */
public class PublicActivity {
	
	private String public_activity_id;
	private String public_activity_creator_id;
	private String public_activity_name;
	private Date public_activity_begin_time;
	private Date public_activity_end_time;
	private int public_activity_target;
	private Date public_activity_create_time;
	private int public_activity_enable;
	private String public_activity_creator_name;
	
	public String getPublic_activity_id() {
		return public_activity_id;
	}
	public void setPublic_activity_id(String public_activity_id) {
		this.public_activity_id = public_activity_id;
	}
	public String getPublic_activity_creator_id() {
		return public_activity_creator_id;
	}
	public void setPublic_activity_creator_id(String public_activity_creator_id) {
		this.public_activity_creator_id = public_activity_creator_id;
	}
	public String getPublic_activity_name() {
		return public_activity_name;
	}
	public void setPublic_activity_name(String public_activity_name) {
		this.public_activity_name = public_activity_name;
	}
	public Date getPublic_activity_begin_time() {
		return public_activity_begin_time;
	}
	public void setPublic_activity_begin_time(Date public_activity_begin_time) {
		this.public_activity_begin_time = public_activity_begin_time;
	}
	public Date getPublic_activity_end_time() {
		return public_activity_end_time;
	}
	public void setPublic_activity_end_time(Date public_activity_end_time) {
		this.public_activity_end_time = public_activity_end_time;
	}
	public int getPublic_activity_target() {
		return public_activity_target;
	}
	public void setPublic_activity_target(int public_activity_target) {
		this.public_activity_target = public_activity_target;
	}
	public Date getPublic_activity_create_time() {
		return public_activity_create_time;
	}
	public void setPublic_activity_create_time(Date public_activity_create_time) {
		this.public_activity_create_time = public_activity_create_time;
	}
	public int getPublic_activity_enable() {
		return public_activity_enable;
	}
	public void setPublic_activity_enable(int public_activity_enable) {
		this.public_activity_enable = public_activity_enable;
	}
	public String getPublic_activity_creator_name() {
		return public_activity_creator_name;
	}
	public void setPublic_activity_creator_name(String public_activity_creator_name) {
		this.public_activity_creator_name = public_activity_creator_name;
	}
	
}
