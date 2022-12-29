package project.carPooling.passenger.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import project.carPooling.passenger.domain.PassengerInfo;
import project.carPooling.passenger.repository.PassengerInfoRepository;

@Service
@RequiredArgsConstructor
public class PassengerLoginService {
	
	private final PassengerInfoRepository passengerRepository;
	
	public PassengerInfo login(String loginId, String password) {
		PassengerInfo passenger = passengerRepository.selectByLoginId(loginId);
		
		if (passenger != null) {
			if (passenger.getpUserPw().equals(password)) {
				return passenger;
			}
		} return null;
	}

}
