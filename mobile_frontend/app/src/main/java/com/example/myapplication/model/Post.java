package com.example.myapplication.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    private int id;
    private String username;
    private String content;
    private String create_at;
    private String image;
    private String time;

    private String update_at;
}
