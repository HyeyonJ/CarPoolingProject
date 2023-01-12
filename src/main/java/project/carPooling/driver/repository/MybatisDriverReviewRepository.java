package project.carPooling.driver.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import project.carPooling.driver.domain.DReview;
import project.carPooling.driver.mapper.DriverReviewMapper;
import project.carPooling.passenger.domain.PReview;

@Slf4j
@Repository
@RequiredArgsConstructor
public class MybatisDriverReviewRepository implements DriverReviewRepository{
	
	private final DriverReviewMapper driverReviewMapper;
	
	@Override
	public void insert(DReview dReview) {
		log.info("review insert 성공");
		driverReviewMapper.insert(dReview);
		double starPoint = dReview.getStarPoint()*0.1;
		driverReviewMapper.updatePassengerTemperature(starPoint, dReview.getToIdx());
		log.info("temperature update 성공");
	}

	@Override
	public List<DReview> selectMyReview(Integer dIdx) {
		List<DReview> dReviewList = driverReviewMapper.selectMyReview(dIdx);
		return dReviewList;
	}

	@Override
	public List<PReview> selectPsReview(Integer dIdx) {
		List<PReview> pReviewList = driverReviewMapper.selectPsReview(dIdx); 
		return pReviewList;
	}


}
