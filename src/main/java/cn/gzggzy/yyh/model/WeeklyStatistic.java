package cn.gzggzy.yyh.model;

public class WeeklyStatistic {
	
	private String pid;
	private String uid;
	private int year;
	private int month;
	private int year_week;
	private int month_week;
	private int week_total;
	private int week_rank;
	private int is_activity;
	private int is_self_activity;
	
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
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getYear_week() {
		return year_week;
	}
	public void setYear_week(int year_week) {
		this.year_week = year_week;
	}
	public int getMonth_week() {
		return month_week;
	}
	public void setMonth_week(int month_week) {
		this.month_week = month_week;
	}
	public int getWeek_total() {
		return week_total;
	}
	public void setWeek_total(int week_total) {
		this.week_total = week_total;
	}
	public int getWeek_rank() {
		return week_rank;
	}
	public void setWeek_rank(int week_rank) {
		this.week_rank = week_rank;
	}
	public int getIs_activity() {
		return is_activity;
	}
	public void setIs_activity(int is_activity) {
		this.is_activity = is_activity;
	}
	public int getIs_self_activity() {
		return is_self_activity;
	}
	public void setIs_self_activity(int is_self_activity) {
		this.is_self_activity = is_self_activity;
	}
	
	@Override
	public String toString() {
		return "WeeklyStatistic{" +
                "pid=" + pid +
                ", uid=" + uid +
                ", year='" + year +
                ", month='" + month +
                ", year_week='" + year_week +
                ", month_week='" + month_week +
                ", week_total='" + week_total +
                ", week_rank='" + week_rank +
                ", is_activity='" + is_activity +
                ", is_self_activity='" + is_self_activity +
                '}';
	}
}
