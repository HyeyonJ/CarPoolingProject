package project.carPooling.passenger.repository;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import project.carPooling.passenger.domain.PReview;
import project.carPooling.passenger.mapper.PassengerReviewMapper;

@Slf4j
@Repository @Primary
@RequiredArgsConstructor
public class MybatisPassengerReviewRepository implements PassengerReviewRepository {

	private final PassengerReviewMapper passengerReviewMapper;
	
	@Override
	public void insert(PReview pReview) {
		log.info("insert성공");
		passengerReviewMapper.insert(pReview);
		double starPoint = pReview.getStarPoint()*0.1;
		passengerReviewMapper.updateDriverTemperature(starPoint, pReview.getToIdx());
		log.info("temperature update 성공");
	}

	@Override
	public void updateCompleteStatus(Integer rIdx) {
		Integer drIdx = passengerReviewMapper.selectDrIdxByRIdx(rIdx);
		passengerReviewMapper.updateCompleteStatus(drIdx);
	}

}
