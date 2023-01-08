package project.carPooling.driver.controller.socialLogin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpServletRequest;

@RequestMapping("/driver")
@Controller
public class DrvGoogleLoginController {

	
	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public String test(HttpServletRequest req, RedirectAttributes rAttr) {
		String email = req.getParameter("email");
		rAttr.addFlashAttribute("email", email);
		return "redirect:/driver/test";
	}
	
	@RequestMapping(value="/test")
	public String test1() {
		return "driver/join/dGoogleCallback";
	}
	
	

}
