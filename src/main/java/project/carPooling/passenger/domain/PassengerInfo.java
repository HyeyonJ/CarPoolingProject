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
	private Boolean pSignOut;

	private String pIdNum;
	
	//Mybatis insert 시 No setter ... 에러 발생 > getter, setter 수동 생성
	public Integer getPIdx() {
		return pIdx;
	}

	public void setPIdx(Integer pIdx) {
		this.pIdx = pIdx;
	}

	public PUserType getPUserType() {
		return pUserType;
	}

	public void setPUserType(PUserType pUserType) {
		this.pUserType = pUserType;
	}

	public String getPUserId() {
		return pUserId;
	}

	public void setPUserId(String pUserId) {
		this.pUserId = pUserId;
	}

	public String getPUserPw() {
		return pUserPw;
	}

	public void setPUserPw(String pUserPw) {
		this.pUserPw = pUserPw;
	}

	public String getPUserName() {
		return pUserName;
	}

	public void setPUserName(String pUserName) {
		this.pUserName = pUserName;
	}

	public String getPUserTel() {
		return pUserTel;
	}

	public void setPUserTel(String pUserTel) {
		this.pUserTel = pUserTel;
	}

	public String getPUserEmail() {
		return pUserEmail;
	}

	public void setPUserEmail(String pUserEmail) {
		this.pUserEmail = pUserEmail;
	}

	public String getPUserGender() {
		return pUserGender;
	}

	public void setPUserGender(String pUserGender) {
		this.pUserGender = pUserGender;
	}

	public String getPIdNum() {
		return pIdNum;
	}

	public void setPIdNum(String pIdNum) {
		this.pIdNum = pIdNum;
	}

	public Boolean getPSignOut() {
		return pSignOut;
	}

	public void setPSignOut(Boolean pSignOut) {
		this.pSignOut = pSignOut;
	}
	
}
