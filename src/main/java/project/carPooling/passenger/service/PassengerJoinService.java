package project.carPooling.passenger.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import project.carPooling.passenger.domain.PassengerInfo;
import project.carPooling.passenger.repository.PassengerInfoRepository;

@Service
@RequiredArgsConstructor
public class PassengerJoinService {

	private final PassengerInfoRepository passengerRepository;
	
	public boolean passengerEmailCheck(String email) {
		
        PassengerInfo passenger = passengerRepository.selectByLoginId(email);
        
        if(passenger!=null) { return true; }
        else { return false; }
        
    }
	
	public boolean passengerUserCheck(String email, String userName) {
		
        PassengerInfo passenger = passengerRepository.selectByLoginId(email);
        
        if(passenger!=null && passenger.getPUserName().equals(userName)) { return true; }
        else { return false; }
        
    }
	


}
