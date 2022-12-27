package project.carPooling.passenger.domain;

import lombok.Data;

@Data
public class PassengerInfo {
	private Integer pIdx;
	private PUserType pUserType;
	private String pUserId;
	private String pUserPw;
	private String pUserName;
	private String pUserTel;
	private String pUserGender;
	private String pUserEmail;
	private Boolean pUserVerify;
	private String pUserVcode;
	private String pIdNum1;
	private String pIdNum2;
	
	private Boolean pSignOut;
}
