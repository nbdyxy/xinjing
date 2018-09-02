package cn.gzggzy.yyh.model;

public class YearlyStatistic {
	
	private String pid;
	private String uid;
	private int year;
	private int year_total;
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
                ", year_rank='" + year_rank +
                ", is_activity='" + is_activity +
                '}';
	}
}
