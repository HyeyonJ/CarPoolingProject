package project.carPooling.passenger.repository;

import java.util.List;

import project.carPooling.driver.domain.DRegistration;
import project.carPooling.passenger.domain.SearchCarPool;

public interface SearchCarpoolRepository {
	public List<DRegistration> selectCarpool(SearchCarPool searchCarPool);
}
