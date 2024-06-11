package com.example.myapplication.network.model.dto;

import java.util.HashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestNotificationDTO {
    private int next;

    public Map<String, String> toMap() {
        Map<String, String> map = new HashMap<>();
        map.put("next", String.valueOf(next));
        return map;
    }
}
