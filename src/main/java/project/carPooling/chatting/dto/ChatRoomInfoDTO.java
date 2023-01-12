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
    private String roomId;
    private String name;
    private Integer rIdx;
    private Integer pIdx;
    private Integer dIdx;

    public static ChatRoomInfoDTO toChatRoomInfoDTO(ChatRoomEntity chatRoomEntity){
        ChatRoomInfoDTO chatRoomInfoDTO = new ChatRoomInfoDTO();

        chatRoomInfoDTO.setChatRoomId(chatRoomEntity.getId());
        chatRoomInfoDTO.setRoomId(chatRoomEntity.getRoomId());
        chatRoomInfoDTO.setName(chatRoomEntity.getRoomName());
        chatRoomInfoDTO.setRIdx(chatRoomEntity.getRIdx());
        chatRoomInfoDTO.setPIdx(chatRoomEntity.getPIdx());
        chatRoomInfoDTO.setDIdx(chatRoomEntity.getDIdx());
        return chatRoomInfoDTO;
    }

}