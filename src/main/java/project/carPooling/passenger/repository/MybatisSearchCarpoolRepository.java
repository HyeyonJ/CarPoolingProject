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
			System.out.println("카풀찾기성공");
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return dRegistrationList;
	}

	@Override
	public DRegistration selectCarpoolByDrIdx(Integer drIdx) {
		DRegistration dRegistration = searchCarpoolMapper.selectCarpoolByDrIdx(drIdx);
		log.info("dRegistration {}", dRegistration);
		System.out.println("예약할 카풀찾기성공");
		return dRegistration;
	}

	@Override
	public DRegistration insert(Integer pIdx, Integer drIdx) {
		searchCarpoolMapper.insert(pIdx, drIdx);
		System.out.println("예약성공");
		return null;
	}
	

}
