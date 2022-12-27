package project.carPooling.driver.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DriverInfo {
<<<<<<< HEAD
	
	private int dIdx;
	private UserType dUserType;
	
=======
	private Integer dIdx;
	private UserType dUserType;

>>>>>>> ff182bcc64c84c90163adb31c9eb3075dfb5dc7c
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
<<<<<<< HEAD
	
	private Boolean dSignOut;
	
=======

	private Boolean dSignOut;

>>>>>>> ff182bcc64c84c90163adb31c9eb3075dfb5dc7c
	private String dLicenseNum;
	private String dLicenseDate;
	private String dCarNum;
	private CarType dCarType;
<<<<<<< HEAD

=======
>>>>>>> ff182bcc64c84c90163adb31c9eb3075dfb5dc7c
}
