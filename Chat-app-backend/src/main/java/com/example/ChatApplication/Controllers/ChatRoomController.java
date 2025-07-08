package com.example.ChatApplication.Controllers;

import com.example.ChatApplication.Model.ChatRoom;
import com.example.ChatApplication.Model.User;
import com.example.ChatApplication.Service.ChatRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/chatrooms")
public class ChatRoomController {

    @Autowired
    private ChatRoomService chatRoomService;

    @PostMapping
    public ResponseEntity<?> createChatRoom(@RequestBody ChatRoom chatRoom) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() || !(authentication.getPrincipal() instanceof User)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "User not authenticated"));
        }
        User currentUser = (User) authentication.getPrincipal();

        // Add the current user to the chat room's users list
        chatRoom.getUsers().add(currentUser);

        try {
            ChatRoom createdChatRoom = chatRoomService.createChatRoom(chatRoom);
            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message", "Chat room created successfully", "chatRoom", createdChatRoom));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("message", "Error creating chat room: " + e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<?> getUserChatRooms() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() || !(authentication.getPrincipal() instanceof User)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "User not authenticated"));
        }
        User currentUser = (User) authentication.getPrincipal();

        try {
            List<ChatRoom> chatRooms = chatRoomService.getChatRoomsForUser(currentUser.getId());
            return ResponseEntity.ok(Map.of("message", "User chat rooms retrieved successfully", "chatRooms", chatRooms));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("message", "Error retrieving chat rooms: " + e.getMessage()));
        }
    }
}
