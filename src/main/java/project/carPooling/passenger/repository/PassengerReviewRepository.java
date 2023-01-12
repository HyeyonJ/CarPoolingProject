package project.carPooling.passenger.repository;

import java.util.List;

import project.carPooling.driver.domain.DReview;
import project.carPooling.passenger.domain.PReview;

public interface PassengerReviewRepository {

	public void insert(PReview pReview);
	
	public void updateCompleteStatus(Integer rIdx);

	public List<PReview> selectMyReview(Integer pIdx);

	public List<DReview> selectDrReview(Integer pIdx);
	
	public List<PReview> selectUserReview(Integer dIdx);

	public void updateReview(Integer rIdx, String content);

	public void deleteReview(Integer rIdx);
}
