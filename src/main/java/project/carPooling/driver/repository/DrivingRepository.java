package project.carPooling.driver.repository;

import java.util.Map;

import project.carPooling.driver.domain.DRegistration;

public interface DrivingRepository {

	public void updateDrivingRegistration(Integer drIdx);
	
	public DRegistration selectDRegistrationByDrIdx(Integer drIdx);
	
	public Map<String, Object> selectRIdxAndPIdx(Integer drIdx);
	
	public String selectCompleteStatus(Integer rIdx);
}
