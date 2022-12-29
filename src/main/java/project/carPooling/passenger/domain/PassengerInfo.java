package project.carPooling.passenger.domain;

//import lombok.Data;

//@Data
public class PassengerInfo {
	private Integer pIdx;
	private PUserType pUserType;
	private String pUserId;
	private String pUserPw;
	private String pUserName;
	private String pUserTel;
	private String pUserEmail;
	private String pUserGender;
	private String pIdNum1;
	private String pIdNum2;	
	private Boolean pSignOut;
	
	//Mybatis insert 시 No setter ... 에러 발생 > getter, setter 수동 생성
	public Integer getpIdx() {
		return pIdx;
	}

	public void setpIdx(Integer pIdx) {
		this.pIdx = pIdx;
	}

	public PUserType getpUserType() {
		return pUserType;
	}

	public void setpUserType(PUserType pUserType) {
		this.pUserType = pUserType;
	}

	public String getpUserId() {
		return pUserId;
	}

	public void setpUserId(String pUserId) {
		this.pUserId = pUserId;
	}

	public String getpUserPw() {
		return pUserPw;
	}

	public void setpUserPw(String pUserPw) {
		this.pUserPw = pUserPw;
	}

	public String getpUserName() {
		return pUserName;
	}

	public void setpUserName(String pUserName) {
		this.pUserName = pUserName;
	}

	public String getpUserTel() {
		return pUserTel;
	}

	public void setpUserTel(String pUserTel) {
		this.pUserTel = pUserTel;
	}

	public String getpUserEmail() {
		return pUserEmail;
	}

	public void setpUserEmail(String pUserEmail) {
		this.pUserEmail = pUserEmail;
	}

	public String getpUserGender() {
		return pUserGender;
	}

	public void setpUserGender(String pUserGender) {
		this.pUserGender = pUserGender;
	}

	public String getpIdNum1() {
		return pIdNum1;
	}

	public void setpIdNum1(String pIdNum1) {
		this.pIdNum1 = pIdNum1;
	}

	public String getpIdNum2() {
		return pIdNum2;
	}

	public void setpIdNum2(String pIdNum2) {
		this.pIdNum2 = pIdNum2;
	}

	public Boolean getpSignOut() {
		return pSignOut;
	}

	public void setpSignOut(Boolean pSignOut) {
		this.pSignOut = pSignOut;
	}
	
}
