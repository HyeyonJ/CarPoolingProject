package project.carPooling.chatting.repository;

import project.carPooling.chatting.entity.ChatMessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatRepository extends JpaRepository<ChatMessageEntity,Long> {
    // Room ID를 통해 채팅내역 불러오기
    List<ChatMessageEntity> findAllByChatRoomEntity_RoomId(String roomId);
}
