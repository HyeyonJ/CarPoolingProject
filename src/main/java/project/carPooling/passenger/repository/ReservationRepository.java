package project.carPooling.passenger.repository;

import java.util.List;

import project.carPooling.driver.domain.DRegistration;
import project.carPooling.passenger.domain.SearchCarPool;

public interface ReservationRepository {
	public List<DRegistration> selectCarpool(SearchCarPool searchCarPool, Integer pIdx);
	
	public DRegistration selectCarpoolByDrIdx(Integer drIdx);
	
	public DRegistration insert(Integer pIdx, Integer drIdx);
	
	public Integer selectRIdxByDrIdx(Integer drIdx);


}
