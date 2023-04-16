package project.carPooling.chatting.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatMsgSaveDTO {
    private String roomId;
    private String writer;
    private String message;

}