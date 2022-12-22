package project.carPooling.passenger.domain;

import lombok.Data;
import project.carPooling.driver.domain.UserType;

@Data
public class PassengerInfo {
	private Integer userNum;
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
}
