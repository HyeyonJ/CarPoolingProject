package project.carPooling.driver.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import project.carPooling.driver.domain.DRegistration;
import project.carPooling.driver.domain.DriverInfo;

@Mapper
public interface DriverInfoMapper {

	public Integer insert(@Param("driver") DriverInfo driverInfo);
	
	public DriverInfo selectById (int id);
	
	public DriverInfo selectByLoginId (String loginId);
	
	public List<DriverInfo> selectAll();
	
	public void deleteAll();
	
	public DriverInfo selectByIdx(Integer dIdx);
	
	public DRegistration selectByDrIdx(Integer drIdx);
	
	public DriverInfo selectByEmail(String dUserEmail);

	public DriverInfo selectByNameAndEmail(String name, String email);
	
	public DriverInfo selectByNameAndTel(String name, String tel);

	public DriverInfo update(DriverInfo driver);
	
	public DriverInfo selectByLicenseNum(String dLicenseNum);

	public DriverInfo selectByCarNum(String dCarNum);
	
}
