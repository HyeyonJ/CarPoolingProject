package project.carPooling.driver.repository;

import java.util.List;

import org.springframework.context.annotation.Primary;

import project.carPooling.driver.domain.DriverInfo;

@Primary
public interface DriverInfoRepository {

	public DriverInfo insert(DriverInfo driverInfo);
	
	public DriverInfo selectById (int id);
	
	public DriverInfo selectByLoginId (String loginId);
	
	public List<DriverInfo> selectAll();
	
	public void deleteAll();
	
	public DriverInfo selectByIdx(Integer dIdx);
}
