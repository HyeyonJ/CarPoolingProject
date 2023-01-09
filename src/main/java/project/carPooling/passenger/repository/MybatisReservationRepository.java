package project.carPooling.passenger.repository;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import project.carPooling.driver.domain.DRegistration;
import project.carPooling.driver.mapper.RegistrationMapper;
import project.carPooling.passenger.domain.SearchCarPool;
import project.carPooling.passenger.mapper.ReservationMapper;

@Slf4j
@Repository
@RequiredArgsConstructor	
public class MybatisReservationRepository implements ReservationRepository {
	
	private final ReservationMapper reservationMapper;
	
	@Transactional
	@Override
	public List<DRegistration> selectCarpool(SearchCarPool searchCarPool, Integer pIdx) {
		
		List<DRegistration> dRegistrationList = null;
		List<Integer> dIdxList = null;
		java.sql.Date pDate =  java.sql.Date.valueOf(searchCarPool.getPDate());
		String pUserGender = reservationMapper.selectPUserGenderByPIdx(pIdx);

		if(searchCarPool.getPHopeGender().equals("A")) {
			try {
				dRegistrationList = reservationMapper.selectCarpoolByAny(searchCarPool, pDate, pUserGender);
			} catch (Exception e) {
				log.error(e.getMessage());
			}
		} else {
			dIdxList = reservationMapper.selectDIdxByGender(searchCarPool.getPHopeGender());
			if(dIdxList.size() != 0) {
				for(Integer dIdx : dIdxList) {
					try {
						dRegistrationList = reservationMapper.selectCarpoolByGender(searchCarPool, pDate, pUserGender, dIdx);
					} catch (Exception e) {
						log.error(e.getMessage());
					}
				}				
			}
		}
		if(dRegistrationList != null && (dRegistrationList.size() == 0 || dIdxList.size() == 0)) {
			return null;
		}
		
		System.out.println(dIdxList);
		return dRegistrationList;
	}

	@Override
	public DRegistration selectCarpoolByDrIdx(Integer drIdx) {
		DRegistration dRegistration = reservationMapper.selectCarpoolByDrIdx(drIdx);
		log.info("dRegistration {}", dRegistration);
		System.out.println("예약할 카풀찾기성공");
		return dRegistration;
	}

	@Override
	public DRegistration insert(Integer pIdx, Integer drIdx) {
		reservationMapper.insert(pIdx, drIdx);
		reservationMapper.updateWaitingToReservated(drIdx);
		System.out.println("예약성공");
		return null;
	}



}
