package project.carPooling.driver.repository;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import project.carPooling.driver.domain.DRegistration;
import project.carPooling.driver.domain.DReview;
import project.carPooling.driver.mapper.RegistrationListMapper;

@Repository
@RequiredArgsConstructor
public class MybatisRegistrationListRepository implements RegistrationListRepository{
		
	private final RegistrationListMapper registrationListMapper;
	
	@Override
	public List<Map<String, Object>> selectReservatedRgsList(Integer dIdx) {
		List<Map<String, Object>> reservatedRgsList = registrationListMapper.selectReservatedRgsList(dIdx);
		return reservatedRgsList;
	}
	
	@Override
	public List<Map<String, Object>> selectWaitingRgsList(Integer dIdx) {
		List<Map<String, Object>> waitingRgsList = registrationListMapper.selectWaitingRgsList(dIdx);
		return waitingRgsList;
	}
	
	@Override
	public List<Map<String, Object>> selectPastRgsList(Integer dIdx) {
		List<Map<String, Object>> pastRgsList = registrationListMapper.selectPastRgsList(dIdx);
		return pastRgsList;
	}
	
	@Override
	public List<Map<String, Object>> selectCompleteRgsList(Integer dIdx) {
		List<Map<String, Object>> completeRgsList = registrationListMapper.selectCompleteRgsList(dIdx);
		return completeRgsList;
	}
	
	@Override
	public List<Map<String, Object>> selectCanceledRgsList(Integer dIdx) {
		List<Map<String, Object>> canceledRgsList = registrationListMapper.selectCanceledRgsList(dIdx);
		return canceledRgsList;
	}

	@Override
	public void cancelReservatedRegistration(Integer drIdx, Integer pIdx) {
		DRegistration dRegistration = registrationListMapper.selectRegistrationByDrIdx(drIdx);
		Integer dIdx = dRegistration.getDIdx();
		String dDate = dRegistration.getDDate();
		String dStratTime = dRegistration.getDStartTime();
		
		Date today = new Date();
		Locale currentLocale = new Locale("KOREAN", "KOREA");
		String pattern = "yyyyMMddHHmm";
		SimpleDateFormat formatter = new SimpleDateFormat(pattern, currentLocale);
		String formatToday = formatter.format(today);
		DateTimeFormatter dateTimeformatter = DateTimeFormatter.ofPattern(pattern);
		
		String dYear = dDate.substring(0, 4);
		String dMonth = dDate.substring(5, 7);
		String dDay = dDate.substring(8, 10);
		String dHours = dStratTime.substring(0, 2);
		String dMinutes = dStratTime.substring(3, 5);
		
		String nYear = formatToday.substring(0, 4);
		String nMonth = formatToday.substring(4, 6);
		String nDay = formatToday.substring(6, 8);
		String nHours = formatToday.substring(8, 10);
		String nMinutes = formatToday.substring(10, 12);
		
		String driveDate = dYear+dMonth+dDay+dHours+dMinutes;
		String nowDate = nYear+nMonth+nDay+nHours+nMinutes;
		
		LocalDateTime convertDDate = LocalDateTime.parse(driveDate, dateTimeformatter);
		LocalDateTime convertNDate = LocalDateTime.parse(nowDate, dateTimeformatter);
		
		if(convertDDate.minusHours(24).isAfter(convertNDate)) {
			System.out.println("패널티없음");
			registrationListMapper.updateCanceledRegistration(drIdx);
			registrationListMapper.updateCanceledReservation(drIdx, pIdx);
		} else {
			System.out.println("패널티있음");
			registrationListMapper.updatePanalty(dIdx);
			registrationListMapper.updateCanceledRegistration(drIdx);
			registrationListMapper.updateCanceledReservation(drIdx, pIdx);
		}
	}

	@Override
	public String selectPassengerEmail(Integer drIdx) {
		String pUserEmail = registrationListMapper.selectPassengerEmail(drIdx);
		return pUserEmail;
	}

	@Override
	public void updateCanceledRegistration(Integer drIdx) {
		registrationListMapper.updateCanceledRegistration(drIdx);
	}

	@Override
	public Map<String, Object> selectRIdxAndPIdx(Integer drIdx) {
		return registrationListMapper.selectRIdxAndPIdx(drIdx);
	}

	@Override
	public DReview selectReviewExistStatus(Integer rIdx) {
		return registrationListMapper.selectReviewExistStatus(rIdx);
	}
}
