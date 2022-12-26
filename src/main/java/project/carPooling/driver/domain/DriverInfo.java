package project.carPooling.driver.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class DriverInfo {
	
	private int dIdx;
	private UserType dUserType;
	
	private String dUserId;
	private String dUserPw;
	private String dUserName;
	private String dUserTel;
	private String dUserGender;
	private String dUserEmail;
	private Boolean dUserVerify;
	private String dUserVcode;
	private String dIdNum1;
	private String dIdNum2;
	
	private Boolean dSignOut;
	
	private String dLicenseNum;
	private String dLicenseDate;
	private String dCarNum;
	private CarType dCarType;

}
