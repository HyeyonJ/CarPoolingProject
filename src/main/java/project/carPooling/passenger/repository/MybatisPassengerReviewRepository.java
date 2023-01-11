package project.carPooling.passenger.repository;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import project.carPooling.passenger.domain.PReview;
import project.carPooling.passenger.mapper.PassengerReviewMapper;

@Slf4j
@Repository
@RequiredArgsConstructor
public class MybatisPassengerReviewRepository implements PassengerReviewRepository {

	private final PassengerReviewMapper passengerReviewMapper;
	
	@Override
	public void insert(PReview pReview) {
		log.info("insert성공");
		passengerReviewMapper.insert(pReview);
	}


}
