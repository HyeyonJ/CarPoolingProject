package project.carPooling.passenger.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import project.carPooling.passenger.domain.PReview;

@Mapper
public interface PassengerReviewMapper {
	
	public void insert(PReview pReview);
	
	public void updateDriverTemperature(@Param("starPoint") double starPoint, @Param("toIdx") Integer toIdx);
}
