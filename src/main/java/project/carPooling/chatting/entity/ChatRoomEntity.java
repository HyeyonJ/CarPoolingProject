package project.carPooling.chatting.entity;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "chatRoom_table")
public class ChatRoomEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chatRoom_id")
    private Long id;

    @Column
    private String roomId;

    @Column
    private String roomName;
    
    @Column
    private Integer dIdx;
    
    @Column
    private Integer pIdx;
    
    @Column
    private Integer rIdx;

    @OneToMany(mappedBy = "chatRoomEntity", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ChatMessageEntity> chatMessageEntityList = new ArrayList<>();

    public static ChatRoomEntity toChatRoomEntity(String roomName, String roomId, Integer pIdx, 
    											  Integer dIdx, Integer rIdx){
        ChatRoomEntity chatRoomEntity = new ChatRoomEntity();
        chatRoomEntity.setRoomName(roomName);
        chatRoomEntity.setRoomId(roomId);
        chatRoomEntity.setPIdx(pIdx);
        chatRoomEntity.setDIdx(dIdx);
        chatRoomEntity.setRIdx(rIdx);
        return chatRoomEntity;
    }
}

