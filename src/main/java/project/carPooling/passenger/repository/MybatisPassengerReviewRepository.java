package project.carPooling.passenger.repository;

import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import project.carPooling.driver.domain.DReview;
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

	
	@Override
	public List<PReview> selectMyReview(Integer pIdx) {
		List<PReview> pReviewList = passengerReviewMapper.selectMyReview(pIdx);
		return pReviewList;
	}

	@Override
	public List<DReview> selectDrReview(Integer pIdx) {
		List<DReview> dReviewList = passengerReviewMapper.selectDrReview(pIdx);
		return dReviewList;
	}

	@Override
	public void updateReview(Integer rIdx, String content) {
		passengerReviewMapper.updateReview(rIdx, content);
	}

	@Override
	public void deleteReview(Integer rIdx) {
		passengerReviewMapper.deleteReview(rIdx);
	}

}
