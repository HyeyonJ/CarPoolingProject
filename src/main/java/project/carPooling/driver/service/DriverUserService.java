package project.carPooling.driver.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import project.carPooling.driver.repository.DriverInfoRepository;
import project.carPooling.driver.domain.DriverInfo;

@Slf4j
@Service
@RequiredArgsConstructor
public class DriverUserService {
	
	private final DriverInfoRepository driverRepository;
	
	//driver 아이디 중복 체크
	public boolean driverCheckId(String id) {
		boolean checkId = false;
        DriverInfo driver = driverRepository.selectByLoginId(id);
        if(driver!=null) { checkId = true; }        
        return checkId;
    }
	
	//driver 이메일 중복 체크
	public boolean driverCheckEmail(String email) {
		boolean checkEmail = false;
		DriverInfo driver = driverRepository.selectByEmail(email);        
        if(driver!=null) { checkEmail = true; }        
        return checkEmail;        
    }
	
	//driver 면허번호 중복 체크
	public boolean driverCheckLicenseNum(String licenseNum) {
		boolean checkLicenseNum = false;
        DriverInfo driver = driverRepository.selectByLicenseNum(licenseNum);        
        if(driver!=null) { checkLicenseNum = true; }        
        return checkLicenseNum;
    }
	
	//driver 아이디 찾기 < 이름 + 이메일
	public String driverFindIdByNameAndEmail(String name, String email) {		
		DriverInfo driver = driverRepository.selectByNameAndEmail(name, email);
        
        if(driver!=null) {
        	String loginId = driver.getDUserId();
        	log.info("id : {}", loginId);
        	return loginId;
        } else {
        	log.error("해당 유저를 찾을 수 없음");
        	return "일치하는 정보를 찾을 수 없습니다.";
        }        
    }
		
}
