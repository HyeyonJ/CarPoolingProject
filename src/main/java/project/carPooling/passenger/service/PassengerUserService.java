package project.carPooling.passenger.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import project.carPooling.passenger.domain.PassengerInfo;
import project.carPooling.passenger.repository.PassengerInfoRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class PassengerUserService {

	private final PassengerInfoRepository passengerRepository;
	
	//passenger 아이디 중복 체크
	public boolean passengerIdCheck(String id) {
		boolean checkId = false;
        PassengerInfo passenger = passengerRepository.selectByLoginId(id);        
        if(passenger!=null) { checkId = true; }        
        return checkId;
    }
	
	//passenger 이메일 중복 체크
	public boolean passengerEmailCheck(String email) {
		boolean checkEmail = false;
        PassengerInfo passenger = passengerRepository.selectByEmail(email);        
        if(passenger!=null) { checkEmail = true; }        
        return checkEmail;        
    }
	
	//passenger 아이디 찾기 < 이름 + 이메일
	public String passengerFindIdByNameAndEmail(String name, String email) {		
        PassengerInfo passenger = passengerRepository.selectByNameAndEmail(name, email);
        
        if(passenger!=null) {
        	String loginId = passenger.getpUserId();
        	log.info("id : {}", loginId);
        	return loginId;
        } else {
        	log.error("해당 유저를 찾을 수 없음");
        	return "일치하는 정보를 찾을 수 없습니다.";
        }
        
    }
	


}
