package dev.websocket.chat.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import java.util.UUID;

/**
 * 이제는 STOMP를 사용하여 pub/sub 방식으로 구현되기 때문에 기존에 직접 메세지를 보내던 기능을 제거
  */
@Slf4j
@Getter
@AllArgsConstructor
@Builder
public class ChatRoom {
    private String roomId;
    private String name;

    public static ChatRoom of(String name) {
        return ChatRoom.builder()
                .name(name)
                .roomId(UUID.randomUUID().toString())
                .build();
    }
}
