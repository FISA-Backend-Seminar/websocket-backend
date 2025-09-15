package dev.websocket.chat.controller;

import dev.websocket.chat.dto.ChatRoom;
import dev.websocket.chat.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChatRoomController {

    private final ChatRoomService chatRoomService;

    @GetMapping("/rooms-all")
    public String getAllRooms(Model model) {
        List<ChatRoom> chatRooms = chatRoomService.findAll(); // 모든 채팅방 목록을 가져옴
        model.addAttribute("chatRooms", chatRooms); // model 객체에 chatRooms라는 이름으로 담음
        return "chat/home"; // chat/home에 전달 -> HTML 렌더링
    }

    @GetMapping("/rooms")
    @ResponseBody
    // 모든 채팅방 목록을 JSON 형태로 응답
    public List<ChatRoom> getRooms() {
        return chatRoomService.findAll();
    }

    @PostMapping("/room")
    @ResponseBody
    public ChatRoom createRoom(@RequestParam String name) {
        return chatRoomService.createRoom(name);
    }

    @GetMapping("/room/{roomId}")
    @ResponseBody
    public ChatRoom getRoom(@PathVariable String roomId) { // URL 경로에 포함된 roomId
        return chatRoomService.findRoomById(roomId);
        // -> 특정 채답방을 조회
        // -> 특정 채팅방의 정보를 JSON으로 응답
    }

}
