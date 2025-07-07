package com.example.ChatApplication.Service;

import com.example.ChatApplication.Model.Message;
import com.example.ChatApplication.Repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;

    public Message sendTextMessage(String chatRoomId, String senderId, String content) {
        Message message = Message.builder()
                .chatRoomId(chatRoomId)
                .senderId(senderId)
                .content(content)
                .timestamp(new Date())
                .build();

        return messageRepository.save(message);
    }

    public Message sendFileMessage(String chatRoomId, String senderId, MultipartFile file) throws IOException {
        String uploadDir = "uploads/";
        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();

        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        Path filePath = uploadPath.resolve(fileName);
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        Message message = Message.builder()
                .chatRoomId(chatRoomId)
                .senderId(senderId)
                .fileUrl("/" + uploadDir + fileName)
                .timestamp(new Date())
                .build();

        return messageRepository.save(message);
    }

    public List<Message> getMessagesByRoom(String chatRoomId) {
        return messageRepository.findByChatRoomIdOrderByTimestampAsc(chatRoomId);
    }
}
