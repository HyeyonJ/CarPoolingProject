package project.carPooling.driver.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface RegistrationListMapper {
	public List<Map<String, Object>> selectAcceptedRegistrationList(@Param("dIdx") Integer dIdx);
	
	public List<Map<String, Object>> selectAllRegistrationList(@Param("dIdx") Integer dIdx);

	public void deleteRegistration(@Param("drIdx") Integer drIdx);
}
