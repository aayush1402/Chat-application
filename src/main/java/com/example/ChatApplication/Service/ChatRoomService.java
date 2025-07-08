package com.example.ChatApplication.Service;

import com.example.ChatApplication.Model.*;
import com.example.ChatApplication.Repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;
    private final UserRepository userRepository;

    public Optional<ChatRoom> createRoom(String name, String userId) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isEmpty()) return Optional.empty();

        ChatRoom room = ChatRoom.builder()
                .name(name)
                .createdBy(userId)
                .members(new ArrayList<>(List.of(userId)))
                .build();

        ChatRoom saved = chatRoomRepository.save(room);

        User user = userOpt.get();
        user.getJoinedRooms().add(saved.getId());
        userRepository.save(user);

        return Optional.of(saved);
    }

    public boolean joinRoom(String roomId, String userId) {
        Optional<User> userOpt = userRepository.findById(userId);
        Optional<ChatRoom> roomOpt = chatRoomRepository.findById(roomId);

        if (userOpt.isEmpty() || roomOpt.isEmpty()) return false;

        ChatRoom room = roomOpt.get();
        User user = userOpt.get();

        if (!room.getMembers().contains(userId)) {
            room.getMembers().add(userId);
            chatRoomRepository.save(room);
        }

        if (!user.getJoinedRooms().contains(roomId)) {
            user.getJoinedRooms().add(roomId);
            userRepository.save(user);
        }

        return true;
    }

    public List<ChatRoom> getUserRooms(String userId) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isEmpty()) return List.of();

        List<String> roomIds = userOpt.get().getJoinedRooms();
        return chatRoomRepository.findAllById(roomIds);
    }

    public boolean deleteRoom(String roomId, String userId) {
        Optional<ChatRoom> roomOpt = chatRoomRepository.findById(roomId);
        if (roomOpt.isEmpty()) return false;

        ChatRoom room = roomOpt.get();
        if (!room.getCreatedBy().equals(userId)) return false;

        chatRoomRepository.deleteById(roomId);
        return true;
    }
}
