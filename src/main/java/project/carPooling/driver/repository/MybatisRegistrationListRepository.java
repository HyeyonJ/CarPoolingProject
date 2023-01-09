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
import project.carPooling.driver.mapper.RegistrationListMapper;

@Repository
@RequiredArgsConstructor
public class MybatisRegistrationListRepository implements RegistrationListRepository{
		
	private final RegistrationListMapper registrationListMapper;
	
	@Override
	public List<Map<String, Object>> selectReservatedList(Integer dIdx) {
		List<Map<String, Object>> reservatedList = registrationListMapper.selectReservatedList(dIdx);
		return reservatedList;
	}
	
	@Override
	public List<Map<String, Object>> selectWaitingList(Integer dIdx) {
		List<Map<String, Object>> waitingList = registrationListMapper.selectWaitingList(dIdx);
		return waitingList;
	}
	
	@Override
	public List<Map<String, Object>> selectPastList(Integer dIdx) {
		List<Map<String, Object>> pastList = registrationListMapper.selectPastList(dIdx);
		return pastList;
	}

	@Override
	public boolean deleteRegistration(Integer drIdx) {
		boolean result = false;
		try {
			registrationListMapper.deleteRegistration(drIdx);
			result = true;
		} catch (Exception e) {
		}
		return result;
	}

	@Override
	public void cancelReservatedRegistration(Integer drIdx) {
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
			registrationListMapper.deleteRegistration(drIdx);
			registrationListMapper.deleteReservation(drIdx);
		} else {
			System.out.println("패널티있음");
			registrationListMapper.updatePanalty(dIdx);
			registrationListMapper.deleteRegistration(drIdx);
			registrationListMapper.deleteReservation(drIdx);
		}
	}

	@Override
	public String selectPassengerEmail(Integer drIdx) {
		String pUserEmail = registrationListMapper.selectPassengerEmail(drIdx);
		return pUserEmail;
	}

	@Override
	public void cancelWaitingRegistration(Integer drIdx) {
		registrationListMapper.deleteRegistration(drIdx);
	}
}
