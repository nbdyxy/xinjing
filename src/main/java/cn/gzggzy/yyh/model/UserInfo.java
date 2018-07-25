package cn.gzggzy.yyh.model;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotBlank;

public class UserInfo implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String user_id;
	@NotBlank(message="{user.name.notBlank}")
	private String username;
	@NotBlank(message="{user.password.notBlank}")
	private String password;
	private Date first_login_time;
	private Date last_login_time;
	private Integer login_count;
	private String last_login_ip;
	private Integer is_enable;
	private Date create_time;
	private Integer third_party_enable;
	
	
	public UserInfo() {
		super();
	}
	
	public UserInfo(String user_id, String username, String password, Date first_login_time,
			Date last_login_time, Integer login_count, String last_login_ip, Integer is_enable, Date create_time,
			Integer third_party_enable) {
		super();
		this.user_id = user_id;
		this.username = username;
		this.password = password;
		this.first_login_time = first_login_time;
		this.last_login_time = last_login_time;
		this.login_count = login_count;
		this.last_login_ip = last_login_ip;
		this.is_enable = is_enable;
		this.create_time = create_time;
		this.third_party_enable = third_party_enable;
	}
	
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getFirst_login_time() {
		return first_login_time;
	}
	public void setFirst_login_time(Date first_login_time) {
		this.first_login_time = first_login_time;
	}
	public Date getLast_login_time() {
		return last_login_time;
	}
	public void setLast_login_time(Date last_login_time) {
		this.last_login_time = last_login_time;
	}
	public Integer getLogin_count() {
		return login_count;
	}
	public void setLogin_count(Integer login_count) {
		this.login_count = login_count;
	}
	public String getLast_login_ip() {
		return last_login_ip;
	}
	public void setLast_login_ip(String last_login_ip) {
		this.last_login_ip = last_login_ip;
	}
	public Integer getIs_enable() {
		return is_enable;
	}
	public void setIs_enable(Integer is_enable) {
		this.is_enable = is_enable;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public Integer getThird_party_enable() {
		return third_party_enable;
	}
	public void setThird_party_enable(Integer third_party_enable) {
		this.third_party_enable = third_party_enable;
	}
	
	@Override
	public String toString() {
		return "UserInfo{" +
                "user_id=" + user_id +
                ", username=" + username +
                ", password='" + password + '\'' +
                ", last_login_ip='" + last_login_ip + '\'' +
                ", login_count='" + login_count + '\'' +
                '}';
	}
	
	
}
