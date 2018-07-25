package cn.gzggzy.yyh.model;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class RegisterUserInfo implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Size(min=1, max=20, message="{user.name.size}")
	private String username;
	@Size(min=6, max=20, message="{user.password.size}")
	private String password;
	private String confirmpassword;
	
	public RegisterUserInfo(String username, String password, String confirmpassword) {
		super();
		this.username = username;
		this.password = password;
		this.confirmpassword = confirmpassword;
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

	public String getConfirmpassword() {
		return confirmpassword;
	}

	public void setConfirmpassword(String confirmpassword) {
		this.confirmpassword = confirmpassword;
	}

	@Override
	public String toString() {
		return "RegisterUserInfo{" +
                "username=" + username +
                ", password='" + password + '\'' +
                ", confirmpassword='" + confirmpassword + '\'' +
                '}';
	}
	
}
