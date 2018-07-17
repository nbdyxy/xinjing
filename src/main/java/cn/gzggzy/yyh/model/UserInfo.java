package cn.gzggzy.yyh.model;

import java.sql.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserInfo {
	
	private String ADMIN_ID;
	@NotNull
	@Size(min=2, max=30)
	private String USERNAME;
	private String PASSWORD;
	private Date FIRST_LOGIN_TIME;
	private Date LAST_LOGIN_TIME;
	private Integer LOGIN_COUNT;
	private String LAST_LOGIN_IP;
	private Integer IS_ENABLE;
	private Date CREATE_TIME;
	private Integer THIRD_PARTY_ENABLE;
	
	
	public String getADMIN_ID() {
		return ADMIN_ID;
	}
	public void setADMIN_ID(String aDMIN_ID) {
		ADMIN_ID = aDMIN_ID;
	}
	public String getUSERNAME() {
		return USERNAME;
	}
	public void setUSERNAME(String uSERNAME) {
		USERNAME = uSERNAME;
	}
	public String getPASSWORD() {
		return PASSWORD;
	}
	public void setPASSWORD(String pASSWORD) {
		PASSWORD = pASSWORD;
	}
	public Date getFIRST_LOGIN_TIME() {
		return FIRST_LOGIN_TIME;
	}
	public void setFIRST_LOGIN_TIME(Date fIRST_LOGIN_TIME) {
		FIRST_LOGIN_TIME = fIRST_LOGIN_TIME;
	}
	public Date getLAST_LOGIN_TIME() {
		return LAST_LOGIN_TIME;
	}
	public void setLAST_LOGIN_TIME(Date lAST_LOGIN_TIME) {
		LAST_LOGIN_TIME = lAST_LOGIN_TIME;
	}
	public Integer getLOGIN_COUNT() {
		return LOGIN_COUNT;
	}
	public void setLOGIN_COUNT(Integer lOGIN_COUNT) {
		LOGIN_COUNT = lOGIN_COUNT;
	}
	public String getLAST_LOGIN_IP() {
		return LAST_LOGIN_IP;
	}
	public void setLAST_LOGIN_IP(String lAST_LOGIN_IP) {
		LAST_LOGIN_IP = lAST_LOGIN_IP;
	}
	public Integer getIS_ENABLE() {
		return IS_ENABLE;
	}
	public void setIS_ENABLE(Integer iS_ENABLE) {
		IS_ENABLE = iS_ENABLE;
	}
	public Date getCREATE_TIME() {
		return CREATE_TIME;
	}
	public void setCREATE_TIME(Date cREATE_TIME) {
		CREATE_TIME = cREATE_TIME;
	}
	public Integer getTHIRD_PARTY_ENABLE() {
		return THIRD_PARTY_ENABLE;
	}
	public void setTHIRD_PARTY_ENABLE(Integer tHIRD_PARTY_ENABLE) {
		THIRD_PARTY_ENABLE = tHIRD_PARTY_ENABLE;
	}
	
}
