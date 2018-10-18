package cn.gzggzy.yyh.model;

import java.io.Serializable;
import java.util.Date;

import cn.gzggzy.yyh.util.DateUtils;

public class PersonalCountOff implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String pid;
	private String uid;
	private String uName;
	private Date record_date;
	private int record_number;
	private String public_activity_id;
	private String private_activity_id;
	
	public PersonalCountOff() {
		super();
	}
	
	public PersonalCountOff(String pid, String uid, Date record_date, int record_number) {
		super();
		this.pid = pid;
		this.uid = uid;
		this.record_date = record_date;
		this.record_number = record_number;
	}
	
	public PersonalCountOff(String pid, String uid, String uName, Date record_date, int record_number) {
		super();
		this.pid = pid;
		this.uid = uid;
		this.uName = uName;
		this.record_date = record_date;
		this.record_number = record_number;
	}
	
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getuName() {
		return uName;
	}
	public void setuName(String uName) {
		this.uName = uName;
	}
	public Date getRecord_date() {
		return record_date;
	}
	public void setRecord_date(Date record_date) {
		this.record_date = record_date;
	}
	public int getRecord_number() {
		return record_number;
	}
	public void setRecord_number(int record_number) {
		this.record_number = record_number;
	}
	public String getPublic_activity_id() {
		return public_activity_id;
	}

	public void setPublic_activity_id(String public_activity_id) {
		this.public_activity_id = public_activity_id;
	}

	public String getPrivate_activity_id() {
		return private_activity_id;
	}

	public void setPrivate_activity_id(String private_activity_id) {
		this.private_activity_id = private_activity_id;
	}

	@Override
	public String toString() {
		return "PersonalCountOff: ["
				+ "pid= " + this.pid 
				+ ", uid= " + this.uid
				+ ", record_date= " + DateUtils.parseDateToStr(this.record_date, "yyyy-MM-dd HH:mm:ss")
				+ ", record_number= " + this.record_number
				+ "]";
	}
}
