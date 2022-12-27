package project.carPooling.passenger.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import project.carPooling.passenger.domain.PassengerInfo;
import project.carPooling.passenger.repository.MybatisPassengerInfoRepository;

@Service
@RequiredArgsConstructor
public class PassengerLoginService {
	
	private final MybatisPassengerInfoRepository mybatisPassengerRepository;
	
	public PassengerInfo login(String loginId, String password) {
		PassengerInfo passenger = mybatisPassengerRepository.selectByLoginId(loginId);
		
		if (passenger != null) {
			if (passenger.getPUserPw().equals(password)) {
				return passenger;
			}
		} return null;
	}

}
