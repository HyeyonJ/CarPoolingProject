package project.carPooling.driver.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import project.carPooling.driver.domain.DReview;
import project.carPooling.passenger.domain.PReview;

@Mapper
public interface DriverReviewMapper {

	public void insert(DReview dReview);

	public void updatePassengerTemperature(@Param("starPoint") double starPoint, @Param("toIdx") Integer toIdx);

	public List<DReview> selectMyReview(Integer dIdx);

	public List<PReview> selectPsReview(Integer dIdx);

	public void updateReview(@Param("rIdx") Integer rIdx, @Param("content") String content);

	public void deleteReview(Integer rIdx);
}
