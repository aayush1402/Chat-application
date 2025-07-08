package com.example.ChatApplication.Model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "messages")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Message {
    @Id
    private String id;



    private String chatRoomId;
    private String senderId;
    private String content;
    private String fileUrl;

    @Builder.Default
    private Date timestamp = new Date();
}
