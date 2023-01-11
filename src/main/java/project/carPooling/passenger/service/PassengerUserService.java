package project.carPooling.passenger.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import project.carPooling.driver.domain.DriverInfo;
import project.carPooling.driver.repository.DriverInfoRepository;
import project.carPooling.passenger.domain.PassengerInfo;
import project.carPooling.passenger.repository.PassengerInfoRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class PassengerUserService {

	private final DriverInfoRepository driverRepository;
	private final PassengerInfoRepository passengerRepository;
	
	//passenger 주민등록번호 중복 체크
	public boolean passengerCheckIdNum(String idNum) {
		boolean checkIdNum = false;
		PassengerInfo passenger = passengerRepository.selectByIdNum(idNum);        
        if(passenger!=null) { checkIdNum = true; }        
        return checkIdNum;
    }
	
	//아이디 통합 중복 체크
	public boolean passengerCheckId(String id) {
		boolean checkId = false;
        DriverInfo driver = driverRepository.selectByLoginId(id);
        PassengerInfo passenger = passengerRepository.selectByLoginId(id);
        if(driver!=null && passenger!=null ) { checkId = true; }        
        return checkId;
    }
	
	//passenger 이메일 중복 체크
	public boolean passengerCheckEmail(String email) {
		boolean checkEmail = false;
        PassengerInfo passenger = passengerRepository.selectByEmail(email);        
        if(passenger!=null) { checkEmail = true; }        
        return checkEmail;        
    }
	
	//passenger 아이디 찾기 < 이름 + 이메일
	public String passengerFindIdByNameAndEmail(String name, String email) {		
        PassengerInfo passenger = passengerRepository.selectByNameAndEmail(name, email);
        
        if(passenger!=null) {
        	String loginId = passenger.getPUserId();
        	log.info("id : {}", loginId);
        	return loginId;
        } else {
        	log.error("해당 유저를 찾을 수 없음");
        	return "일치하는 정보를 찾을 수 없습니다.";
        }        
    }
	
}
