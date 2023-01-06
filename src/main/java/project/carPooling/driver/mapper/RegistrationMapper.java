package project.carPooling.driver.mapper;

import java.sql.Date;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import project.carPooling.driver.domain.DRegistration;

@Mapper
public interface RegistrationMapper {

	public Integer insert(@Param(value="dRegistration") DRegistration dRegistration, @Param(value = "convertDDate") Date convertDDate);
	public DRegistration selectRegistrationByTime(@Param(value="dRegistration") DRegistration dRegistration, @Param(value = "convertDDate") Date convertDDate);

}
