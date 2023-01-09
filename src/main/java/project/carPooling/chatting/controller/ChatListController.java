package project.carPooling.chatting.controller;

import project.carPooling.chatting.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;                                                                                                                                                                                         
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;

import static project.carPooling.global.session.SessionVar.LOGIN_EMAIL;
import static project.carPooling.global.session.SessionVar.LOGIN_NAME;
import static project.carPooling.global.session.SessionVar.LOGIN_ID;
                                                                                                                                                                                                                                                                                                                  

@Controller
@RequestMapping("/chatting")
@RequiredArgsConstructor
@Log4j2
public class ChatListController {
    private final ChatService cs;
    //채팅방 목록 조회 
    @GetMapping("/myChatting/{memberId}")
    public String myChatting(@PathVariable ("memberId") String memberId, Model model){
    	
        model.addAttribute("rooms", cs.findAllRooms());

        return "/chatting/myChatting";
    }
    
    //채팅방 개설
    @PostMapping(value = "/room")
    public String create(@RequestParam String name, HttpSession session,Model model){
        String memberId = (String) session.getAttribute(LOGIN_ID);
        String memberName = (String) session.getAttribute(LOGIN_NAME);
        log.info("# Create Chat Room , name: " + name);

        cs.createChatRoomDTO(name,memberName);

        return "redirect:/chatting/myChatting/"+memberId;
    }

    //채팅방 조회
    @GetMapping("/room")
    public void getRoom(@RequestParam String roomId, Model model,HttpSession session){
        String memberId = (String) session.getAttribute(LOGIN_EMAIL);
//        String memberProfileName = ms.findById(memberId).getMemberProfileName();
//        model.addAttribute("memberProfileName",memberProfileName);

        log.info("# get Chat Room, roomID : " + roomId);

        model.addAttribute("room", cs.findRoomById(roomId));
    }
}