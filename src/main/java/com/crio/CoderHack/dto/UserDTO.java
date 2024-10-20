package com.crio.CoderHack.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    
    private String id; 
    private String username;
    private int score;
    private List<String> badges;
}
