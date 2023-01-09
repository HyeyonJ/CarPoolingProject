package project.carPooling.chatting.dto;

import lombok.*;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatRoomDTO {

    private String roomOwner;
    private String roomId;
    private String name;
    private int password;

    private Set<WebSocketSession> sessions = new HashSet<>();
    //해당 세션 = 웹소켓 세션 

    public static ChatRoomDTO create(String name){
        ChatRoomDTO room = new ChatRoomDTO();

        room.roomId = UUID.randomUUID().toString();
        room.name = name;
        return room;
    }
}