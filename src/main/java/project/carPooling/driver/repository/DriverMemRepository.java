package project.carPooling.driver.repository;

import java.util.List;

import project.carPooling.driver.domain.DriverInfo;


public interface DriverMemRepository {
	public DriverInfo insert(DriverInfo driverInfo);
	
	public DriverInfo selectById (int id);
	
	public DriverInfo selectByLoginId (String loginId);
	
	public List<DriverInfo> selectAll();
	
	public void deleteAll();

}