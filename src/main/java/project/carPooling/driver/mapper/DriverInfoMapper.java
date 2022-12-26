package project.carPooling.driver.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import project.carPooling.driver.domain.DriverInfo;

@Mapper
public interface DriverInfoMapper {

	public Integer insert(DriverInfo driverInfo);
	
	public DriverInfo selectById (int id);
	
	public DriverInfo selectByLoginId (String loginId);
	
	public List<DriverInfo> selectAll();
	
	public void deleteAll();
	
	public DriverInfo selectByIdx(Integer dIdx);
	
}
