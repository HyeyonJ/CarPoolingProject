package project.carPooling.chatting.dto;

import project.carPooling.chatting.entity.ChatMessageEntity;
import lombok.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatMsgInfoDTO {

    private Long chatId;
    private Long chatRoomId;

    private String roomId;
    private String writer;
    private String message;
    private LocalDateTime sendDate;

    public static ChatMsgInfoDTO toChatMsgInfoDTO(ChatMessageEntity chatMessageEntity){
        ChatMsgInfoDTO chatMsgInfoDTO = new ChatMsgInfoDTO();

        chatMsgInfoDTO.setChatId(chatMessageEntity.getId());

        chatMsgInfoDTO.setChatRoomId(chatMessageEntity.getChatRoomEntity().getId());
        chatMsgInfoDTO.setRoomId(chatMessageEntity.getChatRoomEntity().getRoomId());

        chatMsgInfoDTO.setWriter(chatMessageEntity.getWriter());
        chatMsgInfoDTO.setMessage(chatMessageEntity.getMessage());
        chatMsgInfoDTO.setSendDate(chatMessageEntity.getSendDate());
        
        return chatMsgInfoDTO;

    }

}