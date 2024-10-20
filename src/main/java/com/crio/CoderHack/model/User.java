package com.crio.CoderHack.model;

import java.util.List;



import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Document(collection = "users")
public class User {
    
    @Id
    private String id;

    private String username;
    private int score;
    private List<String> badges;
    
    public User(String id, String username, int score, List<String> badges) {
        this.id = id;
        this.username = username;
        this.score = score;
        this.badges = badges;
    }

    
}
