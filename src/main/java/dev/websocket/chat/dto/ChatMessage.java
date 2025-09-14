package dev.websocket.chat.dto;

import lombok.*;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatMessage {
    public enum MessageType {
        ENTER, TALK, JOIN
    }

    private MessageType messageType;
    private String roomId;
    private String sender;
    private String message;
}
