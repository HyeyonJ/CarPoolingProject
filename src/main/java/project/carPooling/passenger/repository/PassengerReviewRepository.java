package project.carPooling.passenger.repository;

import project.carPooling.passenger.domain.PReview;

public interface PassengerReviewRepository {

	public void insert(PReview pReview);
	
	public void updateCompleteStatus(Integer rIdx);
	
}
