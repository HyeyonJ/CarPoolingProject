package project.carPooling.passenger.mapper;

import java.sql.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import project.carPooling.driver.domain.DRegistration;
import project.carPooling.passenger.domain.SearchCarPool;

@Mapper
public interface SearchCarpoolMapper {
	
	public List<DRegistration> selectCarpool(@Param(value="searchCarPool") SearchCarPool searchCarPool, @Param(value = "convertPDate") Date convertPDate);
	
}
