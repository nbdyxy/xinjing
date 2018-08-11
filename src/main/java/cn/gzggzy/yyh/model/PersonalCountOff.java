package cn.gzggzy.yyh.model;

import java.io.Serializable;
import java.util.Date;

import cn.gzggzy.yyh.util.DateUtil;

public class PersonalCountOff implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String pid;
	private String uid;
	private Date record_date;
	private int record_number;
	
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
	
	@Override
	public String toString() {
		return "PersonalCountOff: ["
				+ "pid= " + this.pid 
				+ ", uid= " + this.uid
				+ ", record_date= " + DateUtil.toChar(this.record_date, "yyyy-MM-dd HH:mm:ss")
				+ ", record_number= " + this.record_number
				+ "]";
	}
}
