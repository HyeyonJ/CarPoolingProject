package project.carPooling.driver.controller.mail;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import project.carPooling.global.gmail.UserMailService;
import project.carPooling.passenger.controller.mail.PsgMailController;
import project.carPooling.passenger.service.PassengerUserService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/driver")
public class DrvMailController {

}
