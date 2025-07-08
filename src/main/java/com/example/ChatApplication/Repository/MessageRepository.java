package com.example.ChatApplication.Repository;

import com.example.ChatApplication.Model.Message;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MessageRepository extends MongoRepository<Message, String> {
    List<Message> findByChatRoomIdOrderByTimestampAsc(String chatRoomId);
}
