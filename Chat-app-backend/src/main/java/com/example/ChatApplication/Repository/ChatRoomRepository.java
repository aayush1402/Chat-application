package com.example.ChatApplication.Repository;

import com.example.ChatApplication.Model.ChatRoom;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ChatRoomRepository extends MongoRepository<ChatRoom, String> {
    List<ChatRoom> findByUsers_Id(String userId);
}