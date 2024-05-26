package com.example.mobile.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NotificationDTO {
	private String avatar;
	private String name;
	private int userId;
	private String content;
	private long createAt;
	private String type;
	private String postId;
}
