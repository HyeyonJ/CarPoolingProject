package project.carPooling.driver.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import project.carPooling.driver.domain.DReview;

@Mapper
public interface DriverReviewMapper {

	public void insert(DReview dReview);
	
	public void updatePassengerTemperature(@Param("starPoint") double starPoint, @Param("toIdx") Integer toIdx);
}
