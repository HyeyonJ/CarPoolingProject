package project.carPooling.passenger.repository;

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
import project.carPooling.passenger.mapper.ReservationListMapper;

@Repository
@RequiredArgsConstructor
public class MybatisReservationListRepository implements ReservationListRepository{

	private final ReservationListMapper reservationListMapper;
	
	@Override
	public List<Map<String, Object>> selectCurrentList(Integer pIdx) {
		List<Map<String, Object>> currentList = reservationListMapper.selectCurrentList(pIdx);
		return currentList;
	}

	@Override
	public List<Map<String, Object>> selectPastList(Integer pIdx) {
		List<Map<String, Object>> pastList = reservationListMapper.selectPastList(pIdx);
		return pastList;
	}

	@Override
	public boolean deleteRsv(Integer drIdx, Integer pIdx) {
		boolean result = false;
		try {
			reservationListMapper.deleteRsv(drIdx, pIdx);
			result = true;
		} catch (Exception e) {
		}
		return result;
	}

	@Override
	public int cancelCurrentReservation(Integer drIdx) {
		DRegistration dRegistration = reservationListMapper.selectRegistrationByDrIdx(drIdx);
		Integer dIdx = dRegistration.getDIdx();
		String dDate = dRegistration.getDDate();
		String dStratTime = dRegistration.getDStartTime();
		int dFee = dRegistration.getDFee();
		
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
		
		// 예약취소 패널티
		// 24시간 이전 - 없음
		// 24시간 ~ 12시간 - 결제금액 10%
		// 12시간 ~ 6시간 - 결제금액 15%
		// 6시간 ~ 카풀시간 - 결제금액 20%
		if(convertDDate.minusHours(24).isAfter(convertNDate)) {
			System.out.println("패널티없음");
		} else if((convertDDate.minusHours(24).isBefore(convertNDate))
			    && (convertDDate.minusHours(12).isAfter(convertNDate))) {
			System.out.println("결제금액 20%");
			dFee = dFee - (int) (dFee * 0.2);
			System.out.println(dFee);
			reservationListMapper.updateDriverPoint(dIdx, (int) (dFee * 0.2));
		} else if((convertDDate.minusHours(12).isBefore(convertNDate))
			    && (convertDDate.minusHours(6).isAfter(convertNDate))) {
			System.out.println("결제금액 25%");
			dFee = dFee - (int) (dFee * 0.25);
			System.out.println(dFee);
			reservationListMapper.updateDriverPoint(dIdx, (int) (dFee * 0.25));
		} else if((convertDDate.minusHours(6).isBefore(convertNDate))
			    && (convertDDate.isAfter(convertNDate))) {
			System.out.println("결제금액 30%");
			dFee = dFee - (int) (dFee * 0.3);
			System.out.println(dFee);
			reservationListMapper.updateDriverPoint(dIdx, (int) (dFee * 0.3));
		}
		reservationListMapper.updateReservatedToWaiting(drIdx);
		reservationListMapper.deleteReservation(drIdx);
		
		
		return dFee;
	}

	@Override
	public String selectDriverEmail(Integer drIdx) {
		String dUserEmail = reservationListMapper.selectDriverEmail(drIdx);
		return dUserEmail;
	}

}
