package project.carPooling.driver.repository;

import java.util.List;

import project.carPooling.driver.domain.DReview;
import project.carPooling.passenger.domain.PReview;

public interface DriverReviewRepository {

	public void insert(DReview dReview);
	
	public List<DReview> selectMyReview(Integer dIdx);

	public List<PReview> selectPsReview(Integer dIdx);
	
}
