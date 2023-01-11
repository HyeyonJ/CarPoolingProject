package project.carPooling.driver.repository;

import java.util.List;

import project.carPooling.driver.domain.DriverInfo;

public interface DriverInfoRepository {

	public DriverInfo insert(DriverInfo driverInfo);
	
	public DriverInfo selectByIdx(Integer dIdx);
	
	public DriverInfo selectByLoginId (String loginId);
	
	public DriverInfo selectByEmail(String dUserEmail);
	
	public DriverInfo selectByNameAndEmail(String name, String email);
	
	public DriverInfo selectByNameAndTel(String name, String tel);
	
	public DriverInfo selectByNameAndIdnum(String name, String idnum);
	
	public DriverInfo selectByLicenseNum (String dLicenseNum);
	
	public DriverInfo selectByCarNum (String dCarNum);
	
	public List<DriverInfo> selectAll();
	
	public void deleteAll();
	
	public void updateDriverInfo(DriverInfo driverInfo);
	
	public boolean updateDriverSignOut(Integer dIdx);
	
	
}
