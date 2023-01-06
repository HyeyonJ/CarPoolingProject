package project.carPooling.global.payments;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class paymentsController1 {
  
  @GetMapping("/test1")
	public String controller2() {
		return "payment/payment1";
	}
	
	@ResponseBody
	@GetMapping("/test")
	public String controller1() {
		return "hi";
	}
	
	@ResponseBody
	@PostMapping("/test")
	public Map<String, Object> controller(@RequestBody Map<String, Object> obj) {
		System.out.println(obj);
		return obj;
	}
}
