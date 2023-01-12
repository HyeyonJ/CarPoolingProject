package project.carPooling.chatting.controller;

import project.carPooling.chatting.dto.ChatMsgInfoDTO;
import project.carPooling.chatting.dto.ChatMsgSaveDTO;
import project.carPooling.chatting.entity.ChatMessageEntity;
import project.carPooling.chatting.entity.ChatRoomEntity;
import project.carPooling.chatting.repository.ChatRepository;
import project.carPooling.chatting.repository.ChatRoomRepository;
import project.carPooling.chatting.service.ChatService;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import lombok.RequiredArgsConstructor;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ChatController {

    private final SimpMessagingTemplate template;
    private final ChatRepository cr;
    private final ChatRoomRepository crr;
    private final ChatService cs;

    //Client측 SEND Path 
    //stompConfig 에서 설정한 applicationDestinationPrefixes 와 @MessageMapping 경로가 병합됨
    @MessageMapping(value = "/chat/enter")
    public void enter(ChatMsgInfoDTO message) {
    	String enterMember = message.getWriter();

        List<ChatMsgInfoDTO> chatList = cs.findAllChatByRoomId(message.getRoomId());
        if(!(chatList.isEmpty())){
             for(ChatMsgInfoDTO c : chatList ){
                 message.setWriter(c.getWriter());
                 message.setMessage(c.getMessage());
                 template.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
             }
             message.setMessage(enterMember+ "님이 채팅방에 참여하였습니다.");
             message.setWriter(enterMember);
             template.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
         	 } else {
            message.setMessage(message.getWriter() + "님이 채팅방에 참여하였습니다.");
            template.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
        }
        //채팅 저장 
        ChatMsgSaveDTO chatMsgSaveDTO = new ChatMsgSaveDTO(message.getRoomId(),message.getWriter(), message.getMessage());
        ChatRoomEntity chatRoomEntity= crr.findByRoomId(message.getRoomId());
        cr.save(ChatMessageEntity.toChatEntity(chatMsgSaveDTO,chatRoomEntity));

    }

    @MessageMapping(value = "/chat/message")
    public void message(ChatMsgInfoDTO message) {
        template.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
        //test 
        System.out.println("message:" + message);
        //DB에 채팅내용 저장
        ChatRoomEntity chatRoomEntity= crr.findByRoomId(message.getRoomId());
        ChatMsgSaveDTO chatMessageSaveDTO = new ChatMsgSaveDTO(message.getRoomId(),message.getWriter(), message.getMessage());
        cr.save(ChatMessageEntity.toChatEntity(chatMessageSaveDTO,chatRoomEntity));
    }
}
