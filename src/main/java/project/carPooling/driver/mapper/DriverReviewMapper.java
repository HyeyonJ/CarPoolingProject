package project.carPooling.driver.mapper;

import org.apache.ibatis.annotations.Mapper;

import project.carPooling.driver.domain.DReview;

@Mapper
public interface DriverReviewMapper {

	public void insert(DReview dReview);
	
}
