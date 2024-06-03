package com.example.myapplication.network.model.dto;

import java.util.Date;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
@Setter
@Getter
public class NotificationDTO {
    private int avatar;
    private String name;
    private int userId;
    private String content;
    private Date createAt;
    private boolean active;
    private String type;
    private String postId;
}
