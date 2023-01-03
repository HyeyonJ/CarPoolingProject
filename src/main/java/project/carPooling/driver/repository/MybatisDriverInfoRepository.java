package project.carPooling.driver.repository;

import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import project.carPooling.driver.domain.DRegistration;
import project.carPooling.driver.domain.DriverInfo;
import project.carPooling.driver.mapper.DriverInfoMapper;

@Slf4j
@Repository @Primary
@RequiredArgsConstructor	//Mapper 인터페이스 호출하기 위함
public class MybatisDriverInfoRepository implements DriverInfoRepository {

	private final DriverInfoMapper driverMapper;
	
	@Override
	public DriverInfo insert(DriverInfo driverInfo) {
		String dUserGender = driverInfo.getDUserGender();
		if(dUserGender != null) {
			if(dUserGender.equals("male")) { driverInfo.setDUserGender("M"); }
			else { driverInfo.setDUserGender("F"); } 
		} driverMapper.insert(driverInfo);
		return driverInfo;
	}

	@Override
	public DriverInfo selectById(int id) {
		DriverInfo driverInfo = driverMapper.selectById(id);
		return driverInfo;
	}

	@Override
	public DriverInfo selectByLoginId(String loginId) {
		DriverInfo driverInfo = driverMapper.selectByLoginId(loginId);
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

	@Override
	public DriverInfo selectByIdx(Integer dIdx) {
		DriverInfo driverInfo = driverMapper.selectByIdx(dIdx);
		return driverInfo;
	}

	@Override
	public DriverInfo selectByEmail(String dUserEmail) {
		DriverInfo driverInfo = driverMapper.selectByEmail(dUserEmail);
		return driverInfo;
	}

	@Override
	public DRegistration selectByDrIdx(Integer drIdx) {
		DRegistration dRegistration = driverMapper.selectByDrIdx(drIdx);
		return dRegistration;
	}

	@Override
	public DriverInfo selectByNameAndEmail(String name, String email) {
		DriverInfo driver = driverMapper.selectByNameAndEmail(name, email);
		return driver;
	}

	@Override
	public DriverInfo selectByNameAndTel(String name, String tel) {
		DriverInfo driver = driverMapper.selectByNameAndTel(name, tel);
		return driver;
	}

	@Override
	public DriverInfo update(DriverInfo driver) {
		driverMapper.update(driver);
		return driver;
	}

	@Override
	public DriverInfo selectByLicenseNum(String dLicenseNum) {
		DriverInfo driver = driverMapper.selectByLicenseNum(dLicenseNum);
		return driver;
	}

	@Override
	public DriverInfo selectByCarNum(String dCarNum) {
		DriverInfo driver = driverMapper.selectByCarNum(dCarNum);
		return driver;
	}

}
