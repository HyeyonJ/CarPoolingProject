package project.carPooling.passenger.controller.socialLogin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.security.SecureRandom;
import java.util.Map;

import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import project.carPooling.global.session.SessionVar;
import project.carPooling.passenger.domain.PassengerInfo;
import project.carPooling.passenger.repository.PassengerInfoRepository;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/passenger/login")
public class PsgNaverLoginController {

	@Autowired
	private final PassengerInfoRepository passengerInfoRepository;
	
	private String CLIENT_ID = "80RTTYkxaQQE_nLlnxlk"; // 애플리케이션 클라이언트 아이디값";
	private String CLI_SECRET = "Y28XSEjKSi"; // 애플리케이션 클라이언트 시크릿값";
	

	/**
	 * 콜백 페이지 컨트롤러
	 * 
	 * @param session
	 * @param request
	 * @param model
	 * @return
	 * @throws IOException
	 * @throws ParseException
	 */
	@RequestMapping("/naver/redirect")
	public String naverCallback(HttpSession session
			, HttpServletRequest request
			, Model model, HttpServletRequest req
			, @RequestParam(name="redirectURL", defaultValue="/passenger/passengerCarpool/registration") String redirectURL)
				throws IOException, ParseException {

		String code = request.getParameter("code");
		String state = request.getParameter("state");
		String redirectURI = URLEncoder.encode("http://localhost:8080/passener/login/naver/callback", "UTF-8");

		String apiURL;
		apiURL = "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code&";
		apiURL += "client_id=" + CLIENT_ID;
		apiURL += "&client_secret=" + CLI_SECRET;
		apiURL += "&redirect_uri=" + redirectURI;
		apiURL += "&code=" + code;
		apiURL += "&state=" + state;
		
		System.out.println("apiURL=" + apiURL);
		System.out.println("----------------------------");

		String res = requestToServer(apiURL);
		System.out.println("res : " + res);
		
		String accessToken = null;
		if (res != null && !res.equals("")) {
			model.addAttribute("res", res);
			Map<String, Object> parsedJson = new JSONParser(res).parseObject();
			System.out.println(parsedJson);
			session.setAttribute("currentUser", res);
			accessToken = (String) parsedJson.get("access_token");
			session.setAttribute("currentAT", parsedJson.get("access_token"));
//			accessToken = parsedJson.get("access_token").toString();
			session.setAttribute("currentRT", parsedJson.get("refresh_token"));
		} else {
			model.addAttribute("res", "Login failed!");
		}
		
//		String apiURL = "https://openapi.naver.com/v1/nid/me";
//		String headerStr = "Bearer " + parsedJson.get("access_token"); // Bearer 다음에 공백 추가
//		String res = requestToServer(apiURL, headerStr);
		
		//res -> profile -> id, name
		// addAttibute (id, name) view -> view id, name input hidden
		// input value =id, name
		//return "driver/login/dNaverCallback"; 회원가입 입력하는 페이지로 바로 이동
		
		log.info("accessToken: {}", accessToken);
		String getProfileApiURL = "https://openapi.naver.com/v1/nid/me";
		String headerStr = "Bearer " + accessToken; // Bearer 다음에 공백 추가
		String resProfile = requestToServer(getProfileApiURL, headerStr);
		log.info("resPofile {}", resProfile);
		//사용자 아이디랑 닉네임 정보

		//1. String JSON 파싱 원하는 정보 뽑아내기
		//2. addInfo view 에 데이터 맵핑
		// input 타입 -> email
		// input 타입 -> 닉네임
		JsonParser parser = new JsonParser();
		JsonObject obj = (JsonObject)parser.parse(resProfile);
		JsonObject obj1 = (JsonObject) obj.get("response");
		log.info("email: {}", obj1.get("email"));
		log.info("gender: {}", obj1.get("gender"));
		
		PassengerInfo passengerInfo = new PassengerInfo();
		passengerInfo.setPUserEmail(obj1.get("email").getAsString());
		passengerInfo.setPUserGender(obj1.get("gender").getAsString());
		
		model.addAttribute("passengerInfo", passengerInfo);
		
		PassengerInfo passengerInfo2 = passengerInfoRepository.selectByEmail(passengerInfo.getPUserEmail());
		
		if ( passengerInfo2 == null ) {
//			bindingResult.reject("loginForm", "이메일 or 비밀번호");
			return "driver/join/dNaverCallback";
			
		}
		
		HttpSession session1 = req.getSession();
		session1.setAttribute(SessionVar.LOGIN_PASSENGER, passengerInfo2);
//		session1.setMaxInactiveInterval(540);
		
		return "redirect:" + redirectURL;
	}

	/**
	 * 토큰 갱신 요청 페이지 컨트롤러
	 * 
	 * @param session
	 * @param request
	 * @param model
	 * @param refreshToken
	 * @return
	 * @throws IOException
	 * @throws ParseException
	 */
	@RequestMapping("/naver/refreshToken")
	public String refreshToken(HttpSession session, HttpServletRequest request, Model model, String refreshToken)
			throws IOException, ParseException {

		String apiURL;

		apiURL = "https://nid.naver.com/oauth2.0/token?grant_type=refresh_token&";
		apiURL += "client_id=" + CLIENT_ID;
		apiURL += "&client_secret=" + CLI_SECRET;
		apiURL += "&refresh_token=" + refreshToken;

		System.out.println("apiURL=" + apiURL);

		String res = requestToServer(apiURL);
		model.addAttribute("res", res);
		session.invalidate();
		return "passenger/login/pNaverCallback";
	}

	/**
	 * 토큰 삭제 컨트롤러
	 * 
	 * @param session
	 * @param request
	 * @param model
	 * @param accessToken
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/naver/deleteToken")
	public String deleteToken(HttpSession session, HttpServletRequest request, Model model, String accessToken)
			throws IOException {

		String apiURL;

		apiURL = "https://nid.naver.com/oauth2.0/token?grant_type=delete&";
		apiURL += "client_id=" + CLIENT_ID;
		apiURL += "&client_secret=" + CLI_SECRET;
		apiURL += "&access_token=" + accessToken;
		apiURL += "&service_provider=NAVER";

		System.out.println("apiURL=" + apiURL);

		String res = requestToServer(apiURL);
		model.addAttribute("res", res);
		session.invalidate();
		return "passenger/login/pNaverCallback";
	}

	/**
	 * 액세스 토큰으로 네이버에서 프로필 받기
	 * 
	 * @param accessToken
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping("/naver/getProfile")
	public String getProfileFromNaver(String accessToken) throws IOException {
		// 네이버 로그인 접근 토큰;
		String apiURL = "https://openapi.naver.com/v1/nid/me";
		String headerStr = "Bearer " + accessToken; // Bearer 다음에 공백 추가
		String res = requestToServer(apiURL, headerStr);
		return res;
	}

	/**
	 * 세션 무효화(로그아웃)
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping("/naver/invalidate")
	public String invalidateSession(HttpSession session) {
		session.invalidate();
		return "redirect:/naver";
	}

	/**
	 * 서버 통신 메소드
	 * 
	 * @param apiURL
	 * @return
	 * @throws IOException
	 */
	private String requestToServer(String apiURL) throws IOException {
		return requestToServer(apiURL, "");
	}

	/**
	 * 서버 통신 메소드
	 * 
	 * @param apiURL
	 * @param headerStr
	 * @return
	 * @throws IOException
	 */
	private String requestToServer(String apiURL, String headerStr) throws IOException {
		URL url = new URL(apiURL);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");

		System.out.println("header Str: " + headerStr);
		if (headerStr != null && !headerStr.equals("")) {
			con.setRequestProperty("Authorization", headerStr);
		}

		int responseCode = con.getResponseCode();
		BufferedReader br;

		System.out.println("responseCode=" + responseCode);
		System.out.println("asd");

		if (responseCode == 200) { // 정상 호출
			br = new BufferedReader(new InputStreamReader(con.getInputStream()));
		} else { // 에러 발생
			br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
		}
		String inputLine;
		StringBuffer res = new StringBuffer();
		while ((inputLine = br.readLine()) != null) {
			res.append(inputLine);
		}
		br.close();
		con.disconnect();
		if (responseCode == 200) {
			return res.toString();
		} else {
			return null;
		}

	}

}