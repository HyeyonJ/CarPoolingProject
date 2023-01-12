package project.carPooling.chatting.repository;

import project.carPooling.chatting.entity.ChatRoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRoomRepository extends JpaRepository<ChatRoomEntity,Long> {
//roomId로 ChatRoomEntity 불러오기
    ChatRoomEntity findByRoomId(String roomId);
}