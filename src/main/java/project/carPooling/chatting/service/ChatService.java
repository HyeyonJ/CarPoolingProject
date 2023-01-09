package project.carPooling.chatting.service;

import project.carPooling.chatting.dto.ChatMsgInfoDTO;
import project.carPooling.chatting.dto.ChatRoomInfoDTO;

import java.util.List;

public interface ChatService {
	 List<ChatRoomInfoDTO> findAllRooms();

	    ChatRoomInfoDTO findRoomById(String id);
	    //채팅방 생성하기
	    void createChatRoomDTO(String name, String roomOwner);
	    void deleteById(Long chatRoomId);

	    List<ChatMsgInfoDTO> findAllChatByRoomId(String roomId);
	}
