package project.carPooling.driver.service;


import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import project.carPooling.driver.domain.DriverInfo;
import project.carPooling.driver.repository.DriverInfoRepository;
import project.carPooling.driver.repository.MybatisDriverInfoRepository;

@Service
@RequiredArgsConstructor
public class DrvLoginService {
	
	private final MybatisDriverInfoRepository mybatisDriverInfoRepository;
//	private final DriverInfoRepository driverInfoRepository;
	
	public DriverInfo login(String userId, String userPw) {
		DriverInfo driverInfo = mybatisDriverInfoRepository.selectByLoginId(userId);
		
		if(driverInfo != null ) {
			if(driverInfo.getDUserPw().equals(userPw)) {
				return driverInfo;
			}
		}
		return null;	// 없으면 null을 리턴
	}
}


