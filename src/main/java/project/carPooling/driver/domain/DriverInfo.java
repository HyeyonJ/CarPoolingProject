package project.carPooling.driver.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class DriverInfo {
	private int userNum;
	private UserType userType;
	private String userId;
	private String userPw;
	private String userName;
	private String userGender;
	private String userEmail;
	private Boolean userVerify;
	private String userVcode;
	private String idNum1;
	private String idNum2;
	
	private Boolean signOut;
	private String dOption;
	
	private String licenseNum;
	private String licenseDate;
	private String carNum;
	private CarType carType;
}
