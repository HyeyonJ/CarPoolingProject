package project.carPooling.driver.repository;

import java.util.Map;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import project.carPooling.driver.domain.DRegistration;
import project.carPooling.driver.mapper.DrivingMapper;

@RequiredArgsConstructor
@Repository
public class MybatisDrivingRepository implements DrivingRepository{

	private final DrivingMapper drivingMapper;

	@Override
	public void updateDrivingRegistration(Integer drIdx) {
		drivingMapper.updateDrivingRegistration(drIdx);
		
	}

	@Override
	public Map<String, Object> selectRIdxAndPIdx(Integer drIdx) {
		return drivingMapper.selectRIdxAndPIdx(drIdx);
	}

	@Override
	public DRegistration selectDRegistrationByDrIdx(Integer drIdx) {
		return drivingMapper.selectDRegistrationByDrIdx(drIdx);
	}

	@Override
	public String selectCompleteStatus(Integer rIdx) {
		return drivingMapper.selectCompleteStatus(rIdx);
	}
	
}
