package com.example.myapplication.ui.network.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FriendRequest {
    private int userId;
}
