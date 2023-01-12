package project.carPooling.passenger.repository;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

import project.carPooling.driver.domain.DRegistration;

public interface RidingRepository {

	public Integer selectDrivingStatus(Integer pIdx);
	
	public Map<String, Object> selectRIdxAndDIdx(Integer drIdx);
	
	public DRegistration selectDRegistrationByDrIdx(Integer drIdx);
	
	public void updateDPoint(Integer dIdx, int dFee);
}
