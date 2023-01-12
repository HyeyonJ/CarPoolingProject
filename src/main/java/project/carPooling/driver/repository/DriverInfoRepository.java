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
	
	// 회원 정보 수정 update 처리
	public void updateDriverInfo(DriverInfo driverInfo);
	
	// 회원 탈퇴 시 update 처리
	public boolean updateDriverSignOut(String dUserEmail);
	
	public DriverInfo selectByIdNum(String idNum);
	
	public DriverInfo selectDriverAccountInfo(Integer dIdx);
	
	public boolean updateDriverAccountInfo(Integer dIdx);
	
	
}
