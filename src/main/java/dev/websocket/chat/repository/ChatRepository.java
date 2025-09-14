package dev.websocket.chat.repository;

import dev.websocket.chat.dto.ChatRoom;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.*;

@RequiredArgsConstructor
@Repository
public class ChatRepository {
    // 채팅방 데이터를 저장하는 메모리 저장소(DB X)
    // Key: 채팅방 ID, Value: ChatRoom 객체
    // LinkedHashMap을 사용하여 삽입 순서가 유지되도록 함
    private final Map<String, ChatRoom> chatRoomMap = new LinkedHashMap<>();

    // 애플리케이션 실행 시 5개의 기본 채팅방을 미리 생성해 저장
    @PostConstruct
    private void init() {
        for (int i = 0; i < 5; i++) {
            ChatRoom chatRoom = ChatRoom.of("test_" + i);
            chatRoomMap.put(chatRoom.getRoomId(), chatRoom);
        }
    }

    // 새 채팅방을 저장하거나 기존 채팅방을 덮어씀
    public void save(ChatRoom chatRoom) {
        chatRoomMap.put(chatRoom.getRoomId(), chatRoom);
    }

    // 특정 채팅방 상세 조회
    public ChatRoom findById(String roomId) {
        return chatRoomMap.get(roomId); // roomId를 기준으로 특정 채팅방을 반환
    }

    // 채팅방 목록 조회
    public List<ChatRoom> findAll() {
        List<ChatRoom> chatRooms = new ArrayList<>(chatRoomMap.values()); // 저장된 모든 채팅방을 가져옴
        Collections.reverse(chatRooms); // 역순 정렬 -> 최근 생성된 채팅방이 앞쪽에 오도록 반환
        return chatRooms;
    }
}