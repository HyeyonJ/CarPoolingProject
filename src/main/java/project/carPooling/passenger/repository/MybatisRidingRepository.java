package project.carPooling.passenger.repository;

import java.util.Map;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import project.carPooling.driver.domain.DRegistration;
import project.carPooling.passenger.mapper.RidingMapper;

@RequiredArgsConstructor
@Repository
public class MybatisRidingRepository implements RidingRepository{

	private final RidingMapper ridingMapper;

	@Override
	public Integer selectDrivingStatus(Integer pIdx) {
		return ridingMapper.selectDrivingStatus(pIdx);
	}

	@Override
	public Map<String, Object> selectRIdxAndDIdx(Integer drIdx) {
		return ridingMapper.selectRIdxAndDIdx(drIdx);
	}

	@Override
	public DRegistration selectDRegistrationByDrIdx(Integer drIdx) {
		return ridingMapper.selectDRegistrationByDrIdx(drIdx);
	}

	@Override
	public void updateDPoint(Integer dIdx, int dFee) {
		ridingMapper.updateDPoint(dIdx, dFee);
	}
}
