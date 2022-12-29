package project.carPooling.global.session;

import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import project.carPooling.driver.domain.DriverInfo;
import project.carPooling.passenger.domain.PassengerInfo;

@Component
public class SessionManager {
	
	public PassengerInfo getPsSession(HttpServletRequest req) {
		HttpSession session = req.getSession(false);
		PassengerInfo passengerInfo = (PassengerInfo) session.getAttribute(SessionVar.LOGIN_PASSENGER);
		return passengerInfo;
	}
	
	public DriverInfo getDrSession(HttpServletRequest req) {
		HttpSession session = req.getSession(false);
		DriverInfo driverInfo = (DriverInfo) session.getAttribute(SessionVar.LOGIN_DRIVER);
		return driverInfo;
	}

}
