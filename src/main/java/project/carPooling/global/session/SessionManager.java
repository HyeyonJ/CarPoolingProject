package project.carPooling.global.session;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Component;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SessionManager {
	
	public static final String SESSION_COOKIE_NAME = "tempSessionId";	//계속 가져다 쓸 거니깐 상수 선언해둠
	private Map<String, Object> sessionMap = new HashMap<String, Object>();
	
	public void create(Object object, HttpServletResponse resp) {
		String sessionId = UUID.randomUUID().toString();
		sessionMap.put(sessionId, object);
		
		Cookie cookie = new Cookie(SESSION_COOKIE_NAME, sessionId);
		resp.addCookie(cookie);
	}
	
	public void remove(HttpServletRequest req) {
		
		Cookie cookie = findCookie(req, SESSION_COOKIE_NAME);
		if (cookie != null) {
			sessionMap.remove(cookie.getValue());
		}
	}
	
	//req에서 쿠키를 뒤져서 tempSessionId(name)을 찾아서 그 value(UUID)를 찾으면
	//value(UUID)	sessionMap<value(UUID), Member>
	public Object getSessionObject(HttpServletRequest req) {
		
		Cookie cookie = findCookie(req, SESSION_COOKIE_NAME);
		if (cookie != null) {
			return sessionMap.get(cookie.getValue());
		}
		return null;
	}

	//cookie 찾아서 return해주기
	public Cookie findCookie(HttpServletRequest req, String cookieName) {
		if(req.getCookies() != null) {
			for(Cookie cookie : req.getCookies()) {
				if(cookie.getName().equals(cookieName)) {
					return cookie;
				}
			}
		}
		return null;
	}

}
