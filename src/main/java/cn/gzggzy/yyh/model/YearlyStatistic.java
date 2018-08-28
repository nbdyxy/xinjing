package cn.gzggzy.yyh.model;

import java.util.Date;

import cn.gzggzy.yyh.util.DateUtils;

public class YearlyStatistic {
	
	private String pid;
	private String uid;
	private int year;
	private int year_total;
	private Date last_update_time;
	private int year_rank;
	private int is_activity;
	
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
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getYear_total() {
		return year_total;
	}
	public void setYear_total(int year_total) {
		this.year_total = year_total;
	}
	public Date getLast_update_time() {
		return last_update_time;
	}
	public void setLast_update_time(Date last_update_time) {
		this.last_update_time = last_update_time;
	}
	public int getYear_rank() {
		return year_rank;
	}
	public void setYear_rank(int year_rank) {
		this.year_rank = year_rank;
	}
	public int getIs_activity() {
		return is_activity;
	}
	public void setIs_activity(int is_activity) {
		this.is_activity = is_activity;
	}
	
	@Override
	public String toString() {
		return "WeeklyStatistic{" +
                "pid=" + pid +
                ", uid=" + uid +
                ", year='" + year +
                ", year_total='" + year_total +
                ", last_update_time'" + DateUtils.parseDateToStr(last_update_time, "yyyy-MM-dd hh:mm:ss") +
                ", year_rank='" + year_rank +
                ", is_activity='" + is_activity +
                '}';
	}
}
