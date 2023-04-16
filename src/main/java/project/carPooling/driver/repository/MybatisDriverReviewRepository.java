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
		return driverReviewMapper.selectMyReview(dIdx);
	}

	@Override
	public List<PReview> selectPsReview(Integer dIdx) { 
		return driverReviewMapper.selectPsReview(dIdx);
	}
	
	@Override
	public List<DReview> selectUserReview(Integer pIdx) { 
		return driverReviewMapper.selectUserReview(pIdx);
	}
	
	@Override
	public void updateReview(Integer rIdx, String content) {
		driverReviewMapper.updateReview(rIdx, content);
	}

	@Override
	public void deleteReview(Integer rIdx) {
		driverReviewMapper.deleteReview(rIdx);
	}



}
