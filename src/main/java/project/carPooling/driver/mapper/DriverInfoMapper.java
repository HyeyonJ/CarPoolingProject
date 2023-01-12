package project.carPooling.driver.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import project.carPooling.driver.domain.DRegistration;
import project.carPooling.driver.domain.DriverInfo;

@Mapper
public interface DriverInfoMapper {

	public Integer insert(@Param("driverInfo") DriverInfo driverInfo);
	
	public DriverInfo selectByIdx(Integer dIdx);
	
	public DriverInfo selectByLoginId (String loginId);
	
	public DriverInfo selectByEmail(String dUserEmail);
	
	public DriverInfo selectByNameAndEmail(String name, String email);
	
	public DriverInfo selectByNameAndTel(String name, String tel);
	
	public DriverInfo selectByNameAndIdnum(String name, String idnum);
	
	public DriverInfo selectByLicenseNum(String dLicenseNum);
	
	public DriverInfo selectByCarNum(String dCarNum);
	
	public List<DriverInfo> selectAll();

	public void deleteAll();
	
	// 회원 정보 update 처리
	public void updateDriverInfo(DriverInfo driverInfo);
	
	// 회원 탈퇴 시 update 처리
	public void updateDriverSignOut(String dUserEmail);
	
	public DriverInfo selectByIdNum(String idNum);
	
}
