package dev.websocket.chat.controller;

import dev.websocket.chat.dto.ChatRoom;
import dev.websocket.chat.service.ChatRoomService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class ChatRoomController {

    private final ChatRoomService chatRoomService;

    public ChatRoomController(ChatRoomService chatRoomService) {
        this.chatRoomService = chatRoomService;
    }

    @GetMapping("/chat/rooms-all")
    public String getAllRooms(Model model) {
        List<ChatRoom> chatRooms = chatRoomService.findAll(); // 모든 채팅방 목록을 가져옴
        model.addAttribute("chatRooms", chatRooms); // model 객체에 chatRooms라는 이름으로 담음
        return "/chat/home"; // /chat/home에 전달 -> HTML 렌더링
    }

    @GetMapping("/chat/rooms")
    @ResponseBody
    // 모든 채팅방 목록을 JSON 형태로 응답
    public List<ChatRoom> getRooms() {
        return chatRoomService.findAll();
    }

    @PostMapping("/chat/room")
    public String createRoom(@RequestParam String name) { // 파라미터: name
        chatRoomService.createRoom(name); // 채팅방 생성
        return "redirect:/chat/rooms"; // 생성 후 바로 채팅방으로 이동
    }

    @GetMapping("/chat/room/{roomId}")
    @ResponseBody
    public ChatRoom getRoom(@PathVariable String roomId) { // URL 경로에 포함된 roomId
        return chatRoomService.findRoomById(roomId);
        // -> 특정 채답방을 조회
        // -> 특정 채팅방의 정보를 JSON으로 응
    }

}
