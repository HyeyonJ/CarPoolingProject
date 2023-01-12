package project.carPooling.driver.validation;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import project.carPooling.driver.domain.DriverInfo;
import project.carPooling.passenger.domain.PassengerInfo;

@Component
public class DriverValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return PassengerInfo.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		DriverInfo driverInfo = (DriverInfo) target;
		
		if (!StringUtils.hasText(driverInfo.getDUserId())) {
			errors.rejectValue("userId", null, "아이디 필수 입력입니다."); }
		if (!StringUtils.hasText(driverInfo.getDUserPw())) {
			errors.rejectValue("userPw", null, "비밀번호 필수 입력입니다."); }
		if (!StringUtils.hasText(driverInfo.getDUserName())) {
			errors.rejectValue("userName", null, "이름 필수 입력입니다."); }
		if (!StringUtils.hasText(driverInfo.getDUserEmail())) {
			errors.rejectValue("userEmail", null, "이메일 필수 입력입니다."); }
//		if (!StringUtils.hasText(driver.getDUserTel())) {
//			errors.rejectValue("userTel", null, "휴대폰번호 필수 입력입니다."); }

	}

}
