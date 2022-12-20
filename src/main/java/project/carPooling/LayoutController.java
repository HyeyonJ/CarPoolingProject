package project.carPooling;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LayoutController {

	@RequestMapping("/1")
	public String layout1(){
		return "layoutex";
	}

}