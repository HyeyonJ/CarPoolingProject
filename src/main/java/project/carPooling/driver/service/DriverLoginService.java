package project.carPooling.driver.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import project.carPooling.driver.domain.DriverInfo;
import project.carPooling.driver.repository.DriverInfoRepository;

@Service
@RequiredArgsConstructor
public class DriverLoginService {

	private final DriverInfoRepository driverInfoRepository;
	
	public DriverInfo checkLogin(String loginId) {
		DriverInfo driverInfo = driverInfoRepository.selectByLoginId(loginId);		
		if(driverInfo != null ) { return driverInfo; }
		return null;
	}
	
	public DriverInfo login(String loginId, String password) {
		DriverInfo driverInfo = driverInfoRepository.selectByLoginId(loginId);
		if(driverInfo.getDUserPw().equals(password)) { return driverInfo; }
		return null;
	}
}


