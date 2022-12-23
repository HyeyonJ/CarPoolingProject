package project.carPooling.passenger.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import project.carPooling.driver.domain.DRegistration;
import project.carPooling.passenger.domain.SearchCarPool;
import project.carPooling.passenger.mapper.SearchCarpoolMapper;

@Slf4j
@Repository
@RequiredArgsConstructor	
public class MybatisSearchCarpoolRepository implements SearchCarpoolRepository {
	
	private final SearchCarpoolMapper searchCarpoolMapper;
	
	@Override
	public List<DRegistration> selectCarpool(SearchCarPool searchCarPool) {
		
		log.info("searchCarPool: {}", searchCarPool);
		java.sql.Date pDate =  java.sql.Date.valueOf(searchCarPool.getPDate());
		log.info("pDate: {}", pDate);
		
		List<DRegistration> dRegistrationList = null;
		try {
			dRegistrationList = searchCarpoolMapper.selectCarpool(searchCarPool, pDate);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return dRegistrationList;
	}
	

}
