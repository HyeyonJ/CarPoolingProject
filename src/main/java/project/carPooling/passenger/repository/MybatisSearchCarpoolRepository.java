package project.carPooling.passenger.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
	
	@Transactional
	@Override
	public List<DRegistration> selectCarpool(SearchCarPool searchCarPool, Integer pIdx) {
		
		List<DRegistration> dRegistrationList = null;
		java.sql.Date pDate =  java.sql.Date.valueOf(searchCarPool.getPDate());
		String pUserGender = searchCarpoolMapper.selectPUserGenderByPIdx(pIdx);

		if(searchCarPool.getPHopeGender().equals("A")) {
			try {
				dRegistrationList = searchCarpoolMapper.selectCarpoolByAny(searchCarPool, pDate, pUserGender);
				System.out.println("카풀찾기성공");
			} catch (Exception e) {
				log.error(e.getMessage());
			}
		} else {
			List<Integer> dIdxList = searchCarpoolMapper.selectDIdxByGender(searchCarPool.getPHopeGender());
			
			for(Integer dIdx : dIdxList) {
				try {
					dRegistrationList = searchCarpoolMapper.selectCarpoolByGender(searchCarPool, pDate, pUserGender, dIdx);
					System.out.println("카풀찾기성공");
				} catch (Exception e) {
					log.error(e.getMessage());
				}
			}
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

	@Override
	public boolean selectPassenger(Integer pIdx, Integer drIdx) {
		boolean result = false;
		// 이미 예약된 내역이 있으면 true값 반환 
		if(searchCarpoolMapper.selectPassenger(pIdx, drIdx) != null) {
			return true;
		}
		// 첫 예약이면 false값 반환
		return result;
	}

}
