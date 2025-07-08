package com.example.ChatApplication.Controllers;

import com.example.ChatApplication.Model.Message;
import com.example.ChatApplication.Service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @GetMapping("/{chatRoomId}")
    public ResponseEntity<List<Message>> getMessagesForChatRoom(@PathVariable String chatRoomId) {
        List<Message> messages = messageService.getMessagesForChatRoom(chatRoomId);
        return ResponseEntity.ok(messages);
    }
}