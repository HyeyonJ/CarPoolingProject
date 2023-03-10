package project.carPooling.driver.controller.socialLogin;

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
import org.hibernate.usertype.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import project.carPooling.driver.domain.DUserType;
import project.carPooling.driver.domain.DriverInfo;
import project.carPooling.driver.repository.DriverInfoRepository;
import project.carPooling.driver.service.DrvKakaoService;
import project.carPooling.global.session.SessionVar;
import project.carPooling.passenger.domain.PUserType;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/driver/login")
public class DrvNaverLoginController {
	
	@Autowired
	private final DriverInfoRepository driverInfoRepository;
	
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
	public String drvNaverCallback(HttpSession session
								, HttpServletRequest request
								, Model model, HttpServletRequest req
								, @RequestParam(name="redirectURL", defaultValue="/driver/driverCarpool/registration") String redirectURL,
								RedirectAttributes rAttr)
									throws IOException, ParseException {

		String code = request.getParameter("code");
		String state = request.getParameter("state");
		String redirectURI = URLEncoder.encode("http://localhost:8080/driver/login/naver/redirect", "UTF-8");

		System.out.println("NaverController code= " + code);
		
		String apiURL;
		apiURL = "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code&";
		apiURL += "client_id=" + CLIENT_ID;
		apiURL += "&client_secret=" + CLI_SECRET;
		apiURL += "&redirect_uri=" + redirectURI;
		apiURL += "&code=" + code;
		apiURL += "&state=" + state;
		
		System.out.println("apiURL=" + apiURL);
		System.out.println("----------------------------");

		String res = drvRequestToServer(apiURL);
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
		
		String getProfileApiURL = "https://openapi.naver.com/v1/nid/me";
		String headerStr = "Bearer " + accessToken; // Bearer 다음에 공백 추가
		String resProfile = drvRequestToServer(getProfileApiURL, headerStr);

		JsonParser parser = new JsonParser();
		JsonObject obj = (JsonObject)parser.parse(resProfile);
		JsonObject obj1 = (JsonObject) obj.get("response");
		
		DriverInfo driverInfo = new DriverInfo();
		driverInfo.setDUserEmail(obj1.get("email").getAsString());
		driverInfo.setDUserGender(obj1.get("gender").getAsString());
		
		model.addAttribute("driverInfo", driverInfo);
		
		driverInfo = driverInfoRepository.selectByEmail(driverInfo.getDUserEmail());
		
		if(driverInfo != null && driverInfo.getDSignOut() == true) {
			rAttr.addFlashAttribute("signOut", true);
			return "redirect:/driver/login";
		}
		
		if ( driverInfo == null ) {
			return "driver/join/dNaverCallback";
		}
		
		HttpSession session1 = req.getSession();
		session1.setAttribute(SessionVar.LOGIN_DRIVER, driverInfo);
		session.setAttribute(SessionVar.LOGIN_ID, driverInfo.getDIdx());
		session.setAttribute(SessionVar.LOGIN_NAME, driverInfo.getDUserName());
		
		return "redirect:" + redirectURL;
	}
	
	
		@PostMapping("/naver/join")
		public String drvNaverInsert(@ModelAttribute DriverInfo joinData, BindingResult bindingResult, HttpServletRequest req) {
			driverInfoRepository.insert(joinData);
			DriverInfo driverInfo = driverInfoRepository.selectByEmail(joinData.getDUserEmail());
			
			HttpSession session = req.getSession();
			session.setAttribute(SessionVar.LOGIN_DRIVER, driverInfo);
			
			return "driver/dRegistration";
		}
		
		@ModelAttribute("dUserTypes")
		public DUserType[] DUserTypes() {
			return DUserType.values();
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
	public String drvRefreshToken(HttpSession session, HttpServletRequest request, Model model, String refreshToken)
			throws IOException, ParseException {

		String apiURL;

		apiURL = "https://nid.naver.com/oauth2.0/token?grant_type=refresh_token&";
		apiURL += "client_id=" + CLIENT_ID;
		apiURL += "&client_secret=" + CLI_SECRET;
		apiURL += "&refresh_token=" + refreshToken;

		System.out.println("apiURL=" + apiURL);

		String res = drvRequestToServer(apiURL);
		model.addAttribute("res", res);
		session.invalidate();
		return "driver/join/dNaverCallback";
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
	public String drvDeleteToken(HttpSession session, HttpServletRequest request, Model model, String accessToken)
			throws IOException {

		String apiURL;

		apiURL = "https://nid.naver.com/oauth2.0/token?grant_type=delete&";
		apiURL += "client_id=" + CLIENT_ID;
		apiURL += "&client_secret=" + CLI_SECRET;
		apiURL += "&access_token=" + accessToken;
		apiURL += "&service_provider=NAVER";

		System.out.println("apiURL=" + apiURL);

		String res = drvRequestToServer(apiURL);
		model.addAttribute("res", res);
		session.invalidate();
		return "driver/login/dNaverCallback";
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
	public String drvGetProfileFromNaver(String accessToken) throws IOException {
		// 네이버 로그인 접근 토큰;
		String apiURL = "https://openapi.naver.com/v1/nid/me";
		String headerStr = "Bearer " + accessToken; // Bearer 다음에 공백 추가
		String res = drvRequestToServer(apiURL, headerStr);
		return res;
	}

	/**
	 * 세션 무효화(로그아웃)
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping("/naver/invalidate")
	public String drvInvalidateSession(HttpSession session) {
		session.invalidate();
		return "redirect:/home";
	}

	/**
	 * 서버 통신 메소드
	 * 
	 * @param apiURL
	 * @return
	 * @throws IOException
	 */
	private String drvRequestToServer(String apiURL) throws IOException {
		return drvRequestToServer(apiURL, "");
	}

	/**
	 * 서버 통신 메소드
	 * 
	 * @param apiURL
	 * @param headerStr
	 * @return
	 * @throws IOException
	 */
	private String drvRequestToServer(String apiURL, String headerStr) throws IOException {
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