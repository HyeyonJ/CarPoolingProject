package project.carPooling.home;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

	@GetMapping("/")
	public String home(Model model, HttpServletRequest req) {
		HttpSession session = req.getSession(false);
		if (session == null) {
			return "home";
		}
		return "home";
	}
}