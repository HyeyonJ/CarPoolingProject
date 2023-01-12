package project.carPooling.driver.repository;

import java.util.List;
import java.util.Map;

import project.carPooling.driver.domain.DReview;


public interface RegistrationListRepository {
	
	public List<Map<String, Object>> selectReservatedRgsList(Integer dIdx);
	
	public List<Map<String, Object>> selectWaitingRgsList(Integer dIdx);
	
	public List<Map<String, Object>> selectPastRgsList(Integer dIdx);
	
	public List<Map<String, Object>> selectCompleteRgsList(Integer dIdx);
	
	public List<Map<String, Object>> selectCanceledRgsList(Integer dIdx);
	
	public void cancelReservatedRegistration(Integer drIdx, Integer pIdx);
	
	public String selectPassengerEmail(Integer drIdx);
	
	public void updateCanceledRegistration(Integer drIdx);
	
	public Map<String, Object> selectRIdxAndPIdx(Integer drIdx);
	
	public DReview selectReviewExistStatus(Integer rIdx);
}
