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
    private String avatar;
    private String name;
    private int userId;
    private String content;
    private Long createAt;
    private boolean active;
    private String type;
    private int postId;
}
