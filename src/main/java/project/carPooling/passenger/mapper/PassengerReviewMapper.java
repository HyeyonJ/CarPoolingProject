package project.carPooling.passenger.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import project.carPooling.driver.domain.DReview;
import project.carPooling.passenger.domain.PReview;

@Mapper
public interface PassengerReviewMapper {
	
	public void insert(PReview pReview);
	
	public void updateDriverTemperature(@Param("starPoint") double starPoint, @Param("toIdx") Integer toIdx);
	
	public Integer selectDrIdxByRIdx(Integer rIdx);
	
	public void updateCompleteStatus(Integer drIdx);
	
	public List<PReview> selectMyReview(Integer pIdx);

	public List<DReview> selectDrReview(Integer pIdx);

}
