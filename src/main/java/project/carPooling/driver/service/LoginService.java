package project.carPooling.driver.service;


import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import project.carPooling.driver.domain.DriverInfo;
import project.carPooling.driver.repository.DriverMemRepository;

@Service
@RequiredArgsConstructor
public class LoginService {
	
//	private final ListMemberRepository memberRepository;
//	private final MybatisMemberRepository memberRepository;
//	private final DriverMemRepository driverMemRepository;
//	
//	public driver login(String loginId, String password) {
//		DriverInfo driverInfo = driverMemRepository.selectByLoginId(loginId);
//		
//		if(driverInfo != null ) {
//			if(driverInfo.getPassword().equals(password)) {
//				return driverInfo;
//			}
//		}
//		return null;	// 없으면 null을 리턴
//	}
}