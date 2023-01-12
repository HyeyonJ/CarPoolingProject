package project.carPooling.driver.repository;

import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import project.carPooling.driver.domain.DriverInfo;
import project.carPooling.driver.mapper.DriverInfoMapper;

@Slf4j
@Repository @Primary
@RequiredArgsConstructor
public class MybatisDriverInfoRepository implements DriverInfoRepository {

	private final DriverInfoMapper driverMapper;
	
	@Override
	public DriverInfo insert(DriverInfo driverInfo) {
		String dUserGender = driverInfo.getDUserGender();
		if(dUserGender != null) {
			if(dUserGender.equals("male") || dUserGender.equals("M")) {
				driverInfo.setDUserGender("M"); 
			}
			else { driverInfo.setDUserGender("F"); } 
		} driverMapper.insert(driverInfo);
		return driverInfo;
	}

	@Override
	public DriverInfo selectByIdx(Integer dIdx) {
		DriverInfo driverInfo = driverMapper.selectByIdx(dIdx);
		return driverInfo;
	}

	@Override
	public DriverInfo selectByLoginId(String loginId) {
		DriverInfo driverInfo = driverMapper.selectByLoginId(loginId);
		return driverInfo;
	}
	
	@Override
	public DriverInfo selectByEmail(String dUserEmail) {
		DriverInfo driverInfo = driverMapper.selectByEmail(dUserEmail);
		return driverInfo;
	}
	
	@Override
	public DriverInfo selectByNameAndEmail(String name, String email) {
		DriverInfo driverInfo = driverMapper.selectByNameAndEmail(name, email);
		return driverInfo;
	}
	
	@Override
	public DriverInfo selectByNameAndTel(String name, String tel) {
		DriverInfo driverInfo = driverMapper.selectByNameAndTel(name, tel);
		return driverInfo;
	}
	
	@Override
	public DriverInfo selectByNameAndIdnum(String name, String idnum) {
		DriverInfo driverInfo = driverMapper.selectByNameAndIdnum(name, idnum);
		return driverInfo;
	}

	@Override
	public DriverInfo selectByLicenseNum(String dLicenseNum) {
		DriverInfo driverInfo = driverMapper.selectByLicenseNum(dLicenseNum);
		return driverInfo;
	}
	
	@Override
	public DriverInfo selectByCarNum(String dCarNum) {
		DriverInfo driverInfo = driverMapper.selectByCarNum(dCarNum);
		return driverInfo;
	}
	
	@Override
	public List<DriverInfo> selectAll() {
		List<DriverInfo> drivers = driverMapper.selectAll();
		return drivers;
	}
	
	@Override
	public void deleteAll() {
		driverMapper.deleteAll();
	}
	
	// 회원 정보 수정 시 update 처리
	@Override
	public void updateDriverInfo(DriverInfo driverInfo) {
		driverMapper.updateDriverInfo(driverInfo);
//		return driverInfo;
	}
	
	@Override
	public boolean updateDriverSignOut(String dUserEmail) {		
		
		boolean result = false;		
		
		try {
			driverMapper.updateDriverSignOut(dUserEmail);
			result = true;			
		} catch (Exception e) {
		}
		
		return result;
		
	}

	@Override
	public DriverInfo selectByIdNum(String idNum) {
		DriverInfo driverInfo = driverMapper.selectByIdNum(idNum);
		return driverInfo;
	}

	@Override
	public DriverInfo selectDriverAccountInfo(Integer dIdx) {
		DriverInfo driverInfo = driverMapper.selectDriverAccountInfo(dIdx);
		return driverInfo;
	}
	
	@Override
	public boolean updateDriverAccountInfo(Integer dIdx) {		
		boolean result = false;
		try {
			driverMapper.updateDriverAccountInfo(dIdx);
			result = true;			
		} catch (Exception e) { }		
		return result;
	}


}
