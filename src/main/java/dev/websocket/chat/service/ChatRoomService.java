package dev.websocket.chat.service;

import dev.websocket.chat.dto.ChatMessage;
import dev.websocket.chat.dto.ChatRoom;
import dev.websocket.chat.repository.ChatRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class ChatRoomService {

    private final ChatRepository chatRepository;

    // 모든 채팅방 조회
    public List<ChatRoom> findAll() {
        return chatRepository.findAll();
    }

    // roomId로 채팅방 단건 조회
    public ChatRoom findRoomById(String roomId) {
        return chatRepository.findById(roomId);
    }

    // 채팅방 생성(name만 받으면 roomId는 내부에서 UUID로 부여)
    public ChatRoom createRoom(String name) {
        ChatRoom chatRoom = ChatRoom.of(name);
        chatRepository.save(chatRoom);
        log.info("Created room: {} ({})", chatRoom.getRoomId(), chatRoom.getName());
        return chatRoom;
    }
}