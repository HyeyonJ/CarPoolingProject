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
	
	public void updateDriverInfo(DriverInfo driverInfo);

}
