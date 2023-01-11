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
		log.info("insert성공");
		driverReviewMapper.insert(dReview);
	}


}
