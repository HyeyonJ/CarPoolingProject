package project.carPooling.global.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

	@GetMapping("/")
	public String login(Model model) {
//		LoginForm loginForm = new LoginForm();
//		model.addAttribute("loginForm", loginForm);

		return "home";
	}
}
