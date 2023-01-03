package project.carPooling.passenger.mapper;

import java.sql.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import project.carPooling.driver.domain.DRegistration;
import project.carPooling.passenger.domain.SearchCarPool;

@Mapper
public interface SearchCarpoolMapper {
	
	public List<DRegistration> selectCarpoolByGender(@Param(value="searchCarPool") SearchCarPool searchCarPool, @Param(value = "convertPDate") Date convertPDate, @Param(value = "dIdx") Integer dIdx);
	
	public List<DRegistration> selectCarpoolByAny(@Param(value="searchCarPool") SearchCarPool searchCarPool, @Param(value = "convertPDate") Date convertPDate);
	
	public DRegistration selectCarpoolByDrIdx(Integer drIdx);
	
	public Integer insert(@Param(value="pIdx") Integer pIdx, @Param(value="drIdx") Integer drIdx);

	public String selectPassenger(@Param(value="pIdx") Integer pIdx, @Param(value="drIdx") Integer drIdx);

	public List<Integer> selectDIdxByGender(String pUserGender);
}

