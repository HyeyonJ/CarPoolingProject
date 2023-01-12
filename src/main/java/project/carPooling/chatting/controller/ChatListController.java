package project.carPooling.chatting.controller;

import project.carPooling.chatting.service.ChatService;
import project.carPooling.chatting.dto.ChatRoomInfoDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;                                                                                                                                                                                         
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;
import java.util.List;

//import static project.carPooling.global.session.SessionVar.LOGIN_EMAIL;
import static project.carPooling.global.session.SessionVar.LOGIN_NAME;
import static project.carPooling.global.session.SessionVar.LOGIN_ID;
                                                                                                                                                                                                                                                                                                                  

@Controller
@RequestMapping("/chatting")
@RequiredArgsConstructor
@Log4j2
public class ChatListController {
    private final ChatService cs;
    
    //Ajax / 모든 채팅방 조회 
//    @GetMapping("/findAllChattingRooms");
    
    //Passenger 채팅 방 조회
    @GetMapping("/psgChatting/{memberId}")
    public String psgChatting(@PathVariable ("memberId") String memberId, HttpSession session, Model model){
    	List <ChatRoomInfoDTO> roomList = cs.findAllRooms();
        model.addAttribute("rooms", roomList);
        
        return "/chatting/psgChatting";
    }
    
    
    //채팅방 목록 조회(Driver 측)
    @GetMapping("/drvChatting/{memberId}")
    public String drvChatting(@PathVariable ("memberId") String memberId, HttpSession session, Model model){
    	List <ChatRoomInfoDTO> roomList = cs.findAllRooms();
        model.addAttribute("rooms", roomList);
        
        return "/chatting/drvChatting";
    }
    
    
    //Passenger 채팅방 개설
    @PostMapping(value = "/room/ps")
    public String createPs(@RequestParam String name, @RequestParam Integer rIdx, @RequestParam Integer dIdx,HttpSession session,Model model){
        Integer memberId = (Integer) session.getAttribute(LOGIN_ID);
        String memberName = (String) session.getAttribute(LOGIN_NAME);
        log.info("# Create Chat Room , name: " + name);

        cs.createPsChatRoomDTO(name, name, memberId, dIdx, rIdx);

        return "redirect:/chatting/psgChatting/"+memberId;
    }
    //Driver 채팅방 개설
    @PostMapping(value = "/room/dr")
    public String createDr(@RequestParam String name, @RequestParam Integer rIdx, @RequestParam Integer pIdx,HttpSession session,Model model){
        Integer memberId = (Integer) session.getAttribute(LOGIN_ID);
        String memberName = (String) session.getAttribute(LOGIN_NAME);
        log.info("# Create Chat Room , name: " + name);

        cs.createDrChatRoomDTO(name, name, pIdx, memberId, rIdx);

        return "redirect:/chatting/drvChatting/"+memberId;
    }

    //채팅방 조회
    @GetMapping("/room")
    public void getRoom(@RequestParam String roomId, Model model,HttpSession session){
//        String memberId = (String) session.getAttribute(LOGIN_EMAIL);
//        String memberProfileName = ms.findById(memberId).getMemberProfileName();
//        model.addAttribute("memberProfileName",memberProfileName);

        log.info("# get Chat Room, roomID : " + roomId);

        model.addAttribute("room", cs.findRoomById(roomId));
    }
}