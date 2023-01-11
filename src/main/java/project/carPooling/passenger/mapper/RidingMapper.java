package project.carPooling.passenger.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import project.carPooling.driver.domain.DRegistration;

@Mapper
public interface RidingMapper {

	public Integer selectDrivingStatus(Integer pIdx);
	
	public Map<String, Object> selectRIdxAndDIdx(Integer drIdx);
	
	public DRegistration selectDRegistrationByDrIdx(Integer drIdx);
	
	public void updateDPoint(@Param("dIdx") Integer dIdx, @Param("dFee") int dFee);
}
