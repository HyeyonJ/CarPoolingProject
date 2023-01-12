package project.carPooling.driver.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import project.carPooling.driver.domain.DRegistration;

@Mapper
public interface DrivingMapper {

	public void updateDrivingRegistration(Integer drIdx);
	
	public DRegistration selectDRegistrationByDrIdx(Integer drIdx);
	
	public Map<String, Object> selectRIdxAndPIdx(Integer drIdx);
	
	public String selectCompleteStatus(Integer rIdx);
}
