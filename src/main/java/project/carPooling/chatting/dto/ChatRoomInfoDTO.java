package project.carPooling.chatting.dto;


import project.carPooling.chatting.entity.ChatRoomEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatRoomInfoDTO {
    private Long chatRoomId;
    private String roomOwner;
    private String roomId;
    private String name;

    public static ChatRoomInfoDTO toChatRoomInfoDTO(ChatRoomEntity chatRoomEntity){
        ChatRoomInfoDTO chatRoomInfoDTO = new ChatRoomInfoDTO();

        chatRoomInfoDTO.setChatRoomId(chatRoomEntity.getId());
        chatRoomInfoDTO.setRoomOwner(chatRoomEntity.getRoomOwner());
        chatRoomInfoDTO.setRoomId(chatRoomEntity.getRoomId());
        chatRoomInfoDTO.setName(chatRoomEntity.getRoomName());
        return chatRoomInfoDTO;
    }

}