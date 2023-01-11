package project.carPooling.passenger.mapper;

import org.apache.ibatis.annotations.Mapper;

import project.carPooling.passenger.domain.PReview;

@Mapper
public interface PassengerReviewMapper {
	
	public void insert(PReview pReview);
}
