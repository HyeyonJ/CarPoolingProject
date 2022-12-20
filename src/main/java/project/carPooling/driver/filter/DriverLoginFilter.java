package project.carPooling.driver.filter;

import java.io.IOException;

import org.springframework.util.PatternMatchUtils;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import project.carPooling.global.session.SessionVar;

@Slf4j
public class DriverLoginFilter implements Filter{

	//whiteList는 OK인 케이스, blackList는 그 반대의 케이스
	//Login 안 되어있어도 볼 수 있는 페이지 (홈화면, 회원가입페이지, 로그인페이지)
	private static final String[] whiteList = {"/", "/driver", "/driver/join", "/driver/logout"};

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		log.info("LoginFilter Start");
		HttpServletRequest req = (HttpServletRequest)request;
		String uri = req.getRequestURI();
		
		HttpServletResponse resp = (HttpServletResponse)response;
		
		//Login이 안 되어있으면 "/" 페이지로 이동시키기 (그래야하는 경우에만)
		if(!PatternMatchUtils.simpleMatch(whiteList, uri)) {
			//거짓 : whiteList에 없는 경우 > 로그인했는지 체크
			HttpSession session = req.getSession(false);
			if(session == null || session.getAttribute(SessionVar.LOGIN_DRIVER) == null) {
				//Login 안 되어 있음 > 걸러야하는 케이스
				log.info("로그인 없이 접근 시도 {}", uri);
				//로그인 후 원래 이동하려고했던 경로로 이동시키기
				resp.sendRedirect("/driver/login?redirectURL=" + uri);
				//redirect로 보낸 후 종료 > return;
				return;
			}
		}
		chain.doFilter(request, response);
		
		// Login정보가 있으면 수행
		
	}
}
