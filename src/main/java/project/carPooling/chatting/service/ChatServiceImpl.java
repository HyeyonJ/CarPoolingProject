package project.carPooling.chatting.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import project.carPooling.chatting.dto.ChatMsgInfoDTO;
import project.carPooling.chatting.dto.ChatRoomDTO;
import project.carPooling.chatting.dto.ChatRoomInfoDTO;
import project.carPooling.chatting.entity.ChatMessageEntity;
import project.carPooling.chatting.entity.ChatRoomEntity;
import project.carPooling.chatting.repository.ChatRepository;
import project.carPooling.chatting.repository.ChatRoomRepository;



@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService{
    private final ChatRoomRepository crr;
    private final ChatRepository cr;

    //채팅방 전체 리스트 확인 
    @Override
    public List<ChatRoomInfoDTO> findAllRooms(){
        List<ChatRoomEntity> chatRoomEntityList = crr.findAll(Sort.by(Sort.Direction.DESC, "id"));
        List<ChatRoomInfoDTO> chatRoomList = new ArrayList<>();

        for (ChatRoomEntity c : chatRoomEntityList){
            chatRoomList.add(ChatRoomInfoDTO.toChatRoomInfoDTO(c));
        }

        return chatRoomList;
    }
    
    
    
    // 채팅 Room Id로 찾기
    @Override
    public ChatRoomInfoDTO findRoomById(String roomId){
        ChatRoomEntity chatRoomEntity = crr.findByRoomId(roomId);
        ChatRoomInfoDTO chatRoomInfoDTO = ChatRoomInfoDTO.toChatRoomInfoDTO(chatRoomEntity);
        return chatRoomInfoDTO;
    }

    //Passenger 채팅방 생성하기
    @Override
    public void createPsChatRoomDTO(String name, String roomId, Integer pIdx, Integer dIdx, Integer rIdx){
        ChatRoomDTO room = ChatRoomDTO.create(name);
        ChatRoomEntity chatRoomEntity = ChatRoomEntity.toChatRoomEntity(room.getName(),room.getRoomId(), pIdx, dIdx, rIdx);
        crr.save(chatRoomEntity);
    }
    
  //Driver 채팅방 생성하기
    @Override
    public void createDrChatRoomDTO(String name, String roomId, Integer pIdx, Integer dIdx, Integer rIdx){
        ChatRoomDTO room = ChatRoomDTO.create(name);
        ChatRoomEntity chatRoomEntity = ChatRoomEntity.toChatRoomEntity(room.getName(),room.getRoomId(),pIdx, dIdx, rIdx);
        crr.save(chatRoomEntity);
    }

    @Override
    public void deleteById(Long chatRoomId) {
        crr.deleteById(chatRoomId);
    }

    //채팅 이력 확인
    @Override
    public List<ChatMsgInfoDTO> findAllChatByRoomId(String roomId) {
        List<ChatMessageEntity> chatMessageEntityList = cr.findAllByChatRoomEntity_RoomId(roomId);
        List<ChatMsgInfoDTO> chatMessageList = new ArrayList<>();
        for (ChatMessageEntity c:chatMessageEntityList){
            chatMessageList.add(ChatMsgInfoDTO.toChatMsgInfoDTO(c));
        }
        return chatMessageList;
    }


}