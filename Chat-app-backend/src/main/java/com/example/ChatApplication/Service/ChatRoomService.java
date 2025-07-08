package com.example.ChatApplication.Service;

import com.example.ChatApplication.Model.ChatRoom;
import com.example.ChatApplication.Repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;

    public ChatRoom createChatRoom(ChatRoom chatRoom) {
        return chatRoomRepository.save(chatRoom);
    }

    public List<ChatRoom> getChatRoomsForUser(String userId) {
        return chatRoomRepository.findByUsers_Id(userId);
    }
}