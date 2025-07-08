package com.example.ChatApplication.Dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatMessage {
    private String chatRoomId;
    private String senderId;
    private String content;
}