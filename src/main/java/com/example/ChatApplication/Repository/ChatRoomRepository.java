package com.example.ChatApplication.Repository;

import com.example.ChatApplication.Model.ChatRoom;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ChatRoomRepository extends MongoRepository<ChatRoom, String> {}
