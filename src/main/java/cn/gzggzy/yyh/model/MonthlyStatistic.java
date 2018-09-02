package cn.gzggzy.yyh.model;

public class MonthlyStatistic {
	
	private String pid;
	private String uid;
	private int year;
	private int month;
	private int month_total;
	private int month_rank;
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
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getMonth_total() {
		return month_total;
	}
	public void setMonth_total(int month_total) {
		this.month_total = month_total;
	}
	public int getMonth_rank() {
		return month_rank;
	}
	public void setMonth_rank(int month_rank) {
		this.month_rank = month_rank;
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
                ", month='" + month +
                ", month_total='" + month_total +
                ", month_rank='" + month_rank +
                ", is_activity='" + is_activity +
                '}';
	}
}
