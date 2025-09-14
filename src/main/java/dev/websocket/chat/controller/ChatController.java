package dev.websocket.chat.controller;

import dev.websocket.chat.dto.ChatMessage;
import dev.websocket.chat.dto.ChatRoom;
import dev.websocket.chat.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class ChatController {
    private final SimpMessageSendingOperations messagingTemplate;

    @MessageMapping("/chat/message") // 웹소켓으로 들어오는 메시지 발행을 처리
    // prefix인 /pub + /chat/message로 발행 요청을 하면 Controller가 해당 메시지를 받아서 처리
    public void sendMessage(ChatMessage message) {
        if (isJoin(message))
            message.setMessage(message.getSender() + "님이 입장하였습니다");

        // /sub/chat/room + roomId를 구독하고 있는 유저에게 메시지를 보냄
        // SimpMessageSendingOperations안에 convertAndSend 메서드가 내장
        messagingTemplate.convertAndSend("/sub/chat/room" + message.getRoomId(), message);
    }

    private boolean isJoin(ChatMessage messageType) {
        return messageType.getMessageType().equals(ChatMessage.MessageType.JOIN);
    }

}