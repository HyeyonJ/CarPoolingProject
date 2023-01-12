package project.carPooling.passenger.validation;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import project.carPooling.passenger.domain.PassengerInfo;

@Component
public class PassengerValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return PassengerInfo.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		PassengerInfo passenger = (PassengerInfo) target;
		
		if(!StringUtils.hasText(passenger.getPUserId())) {
			errors.rejectValue("userId", null, "아이디 필수 입력입니다.");}
		if(!StringUtils.hasText(passenger.getPUserPw())) {
			errors.rejectValue("userPw", null, "패스워드 필수 입력입니다.");}
		if(!StringUtils.hasText(passenger.getPUserName())) {
			errors.rejectValue("userName", null, "이름 필수 입력입니다.");}
		if(!StringUtils.hasText(passenger.getPUserEmail())) {
			errors.rejectValue("userEmail", null, "이메일 필수 입력입니다.");}
//		if(!StringUtils.hasText(passenger.getPUserTel())) {
//			errors.rejectValue("userTel", null, "휴대폰번호 필수 입력입니다.");}
		
	}

}
