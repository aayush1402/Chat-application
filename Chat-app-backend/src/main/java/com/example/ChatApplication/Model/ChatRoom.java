package com.example.ChatApplication.Model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "chatrooms")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatRoom {
    @Id
    private String id;

    private String name;

    @DBRef
    @Builder.Default
    private List<User> users = new ArrayList<>();
}