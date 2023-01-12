package project.carPooling.passenger.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import project.carPooling.global.session.SessionVar;

@Slf4j
public class PassengerLoginInterceptor implements HandlerInterceptor {
	//컨트롤러가 제대로 불리기전에 먼저 수행
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		String uri = request.getRequestURI();		
		log.info("PassengerLoginIntercepter preHandle {}", uri);
		
		HttpSession session = request.getSession(false);
		if(session == null || session.getAttribute(SessionVar.LOGIN_PASSENGER) == null) {
			log.info("로그인 없이 접근 시도 {}", uri);
			response.sendRedirect("/passenger/login?redirectURL" + uri);
			return false;	//false : 멈춤 ( < 로그인 없이 수행이 되면 안 됨)
		}
		
		return true;	//true : 진행
	}

}
