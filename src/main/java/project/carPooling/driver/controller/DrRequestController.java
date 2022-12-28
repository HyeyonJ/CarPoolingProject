package project.carPooling.driver.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import project.carPooling.driver.repository.RequestRepository;

@RequiredArgsConstructor
@Controller
public class DrRequestController {

	private final RequestRepository requestRepository;
	
	private ObjectMapper mapper = new ObjectMapper();
	
	@GetMapping("/driver/driverCarpooling/reqList")
	public String reqList(){
		return "driver/dRequestList";
	}
	
	@ResponseBody
	@PostMapping("/driver/driverCarpooling/reqList")
	public List<Map<String, Object>> reqList1(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
//		HttpSession session = req.getSession(false);
//		Integer dIdx = (Integer) session.getAttribute("name");
		Integer dIdx = 1;
		
		// 다른것도 js에서 data 받으면 어떻게 들어오는지 날짜 확인해야함
		List<Map<String, Object>> reqList = requestRepository.selectRequestByDIdx(dIdx);
		System.out.println(reqList);
		
		return reqList;
	}
	
}
