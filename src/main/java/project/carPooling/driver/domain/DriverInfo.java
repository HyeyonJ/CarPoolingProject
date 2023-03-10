package project.carPooling.driver.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DriverInfo {
	private Integer dIdx;	
	private DUserType dUserType;	
	private String dUserId;
	private String dUserPw;
	private String dUserName;
	private String dUserTel;
	private String dUserEmail;
	private String dUserGender;
	private String dIdNum;
	private String dLicenseNum;
	private String dLicenseIdNum;
	private String dCarNum;
	private String dAccountNum;
	private String dAccountBank;	
	private int dPoint;
	private double dTemperature;
	private String dPenalty;
	private Boolean dSignOut;

	
	public Integer getDIdx() {
		return dIdx;
	}
	public void setDIdx(Integer dIdx) {
		this.dIdx = dIdx;
	}
	public DUserType getDUserType() {
		return dUserType;
	}
	public void setDUserType(DUserType dUserType) {
		this.dUserType = dUserType;
	}
	public String getDUserId() {
		return dUserId;
	}
	public void setDUserId(String dUserId) {
		this.dUserId = dUserId;
	}
	public String getDUserPw() {
		return dUserPw;
	}
	public void setDUserPw(String dUserPw) {
		this.dUserPw = dUserPw;
	}
	public String getDUserName() {
		return dUserName;
	}
	public void setDUserName(String dUserName) {
		this.dUserName = dUserName;
	}
	public String getDUserTel() {
		return dUserTel;
	}
	public void setDUserTel(String dUserTel) {
		this.dUserTel = dUserTel;
	}
	public String getDUserGender() {
		return dUserGender;
	}
	public void setDUserGender(String dUserGender) {
		this.dUserGender = dUserGender;
	}
	public String getDUserEmail() {
		return dUserEmail;
	}
	public void setDUserEmail(String dUserEmail) {
		this.dUserEmail = dUserEmail;
	}
	public String getDIdNum() {
		return dIdNum;
	}
	public void setDIdNum(String dIdNum) {
		this.dIdNum = dIdNum;
	}
	public Boolean getDSignOut() {
		return dSignOut;
	}
	public void setDSignOut(Boolean dSignOut) {
		this.dSignOut = dSignOut;
	}
	public String getDLicenseNum() {
		return dLicenseNum;
	}
	public void setDLicenseNum(String dLicenseNum) {
		this.dLicenseNum = dLicenseNum;
	}
	public String getDLicenseIdNum() {
		return dLicenseIdNum;
	}
	public void setDLicenseIdNum(String dLicenseIdNum) {
		this.dLicenseIdNum = dLicenseIdNum;
	}
	public String getDCarNum() {
		return dCarNum;
	}
	public void setDCarNum(String dCarNum) {
		this.dCarNum = dCarNum;
	}
	public String getDPenalty() {
		return dPenalty;
	}
	public void setDPenalty(String dPenalty) {
		this.dPenalty = dPenalty;
	}
	public String getdAccountNum() {
		return dAccountNum;
	}
	public void setdAccountNum(String dAccountNum) {
		this.dAccountNum = dAccountNum;
	}
	public String getdAccountBank() {
		return dAccountBank;
	}
	public void setdAccountBank(String dAccountBank) {
		this.dAccountBank = dAccountBank;
	}
	public int getdPoint() {
		return dPoint;
	}
	public void setdPoint(int dPoint) {
		this.dPoint = dPoint;
	}
	public double getdTemperature() {
		return dTemperature;
	}
	public void setdTemperature(double dTemperature) {
		this.dTemperature = dTemperature;
	}

	
}
