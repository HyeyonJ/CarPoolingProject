//package project.carPooling.driver.controller.socialLogin;
//
//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.URI;
//import java.net.URL;
//import java.util.HashMap;
//
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.util.LinkedMultiValueMap;
//import org.springframework.util.MultiValueMap;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.client.RestTemplate;
//
//import com.google.gson.JsonElement;
//import com.google.gson.JsonParser;
//
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import project.carPooling.driver.repository.DriverInfoRepository;
//
//@Slf4j
//@Controller
//@RequiredArgsConstructor
//@RequestMapping("/driver/login")
//public class DrvGoogleLoginController {
//	
//    private static final String CLIENT_ID = "";
//    private static final String CLIENT_SECRET = "";
//    private static final String REDIRECT_URI = "";
//    private static final String GRANT_TYPE = "";
//    private static final String ACCESS_TOKEN_URL = "";
//    
//    
//    /**
//     * Callback 1 : Authorization Code Grant
//     */
//    @RequestMapping("/google/redirect")
//    public String GoogleCallback(HttpServletRequest request
//    							, HttpServletResponse response) throws Exception {
//
////        log.info("## [REQUEST] code ={}", request.getParameter("code"));
//        try {
//            /* Access Token 얻기 */
//            String accessToken = getGoogleAccessToken(request, response);
//            /* 사용자 정보 얻기 */
//            getGoogleUserInfo(accessToken);
//        } catch (Exception e) {
////            log.error(e);
//            throw e;
//        }
//        /* 임시 - 로그인 성공 창 */
//        return "driver/join/dGoogleCallback";
//    }
//    
//    /**
//     * Callback 1 : Authorization Code Grant
//     * Access Token 처리 (+획득)
//     *
//     * @param request
//     * @Param response
//     * @throws Exception
//     * @return Access Token 값 
//     */
//    public String getGoogleAccessToken(HttpServletRequest request, HttpServletResponse response) throws Exception {
//
//        String code = request.getParameter("code");
//
//        HttpHeaders headers = new HttpHeaders();
//        RestTemplate restTemplate = new RestTemplate();
//        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//
//        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
//        parameters.add("code", code);
//        parameters.add("client_id", CLIENT_ID);
//        parameters.add("client_secret", CLIENT_SECRET);
//        parameters.add("redirect_uri", REDIRECT_URI);
//        parameters.add("grant_type", GRANT_TYPE);
//
//        HttpEntity<MultiValueMap<String, String>> rest_request = new HttpEntity<>(parameters, headers);
//
//        URI uri = URI.create(ACCESS_TOKEN_URL);
//
//        ResponseEntity<String> responseEntity;
//        responseEntity = restTemplate.postForEntity(uri, rest_request, String.class);
//        String bodys = responseEntity.getBody();
//        log.info("## BODYS = {}", bodys);
//
//        JsonParser jsonParser = new JsonParser();
//        JsonElement jsonElement = jsonParser.parse(bodys);
//
//        return jsonElement.getAsJsonObject().get("access_token").getAsString();
//    }
//    
//    /**
//     * Callback 1 : Authorization Code Grant
//     * Google 사용자 계정 정보 가져오기
//     *
//     * @param accessToken 획득한 ACCESS TOKEN 값
//     * @throws
//     */
//    public void getGoogleUserInfo(String accessToken) throws Exception {
//
//        //요청하는 클라이언트마다 가진 정보가 다를 수 있기에 HashMap 선언
//        HashMap<String, Object> googleUserInfo = new HashMap<>();
//
//        String reqURL = "https://www.googleapis.com/oauth2/v1/userinfo?alt=json&access_token=" + accessToken;
//        try {
//            URL url = new URL(reqURL);
//            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//
//            //요청에 필요한 Header에 포함될 내용
//            conn.setRequestProperty("Authorization", "Bearer " + accessToken);
//
//            int responseCode = conn.getResponseCode();
//            log.info("## ResponseCode : {}", responseCode);
//
//            if (responseCode == 200) {
//                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//
//                String line = "";
//                String result = "";
//                while ((line = br.readLine()) != null) {
//                    result += line;
//                }
//
//                JsonParser parser = new JsonParser();
//                log.info("## Result = {}", result);
//
//                JsonElement element = parser.parse(result);
//                String name = element.getAsJsonObject().get("name").getAsString();
//                String email = element.getAsJsonObject().get("email").getAsString();
//                String id = "GOOGLE_" + element.getAsJsonObject().get("id").getAsString();
//
//                googleUserInfo.put("name", name);
//                googleUserInfo.put("email", email);
//                googleUserInfo.put("id", id);
//
//                log.info("## Login Controller : {}", googleUserInfo);
//            }
//        } catch (Exception e) {
////            log.error(e);
//            throw e;
//        }
//
//    }
//	
//}
