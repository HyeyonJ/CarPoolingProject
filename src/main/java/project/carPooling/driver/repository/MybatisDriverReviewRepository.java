package project.carPooling.driver.repository;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import project.carPooling.driver.domain.DReview;
import project.carPooling.driver.mapper.DriverReviewMapper;

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


}
