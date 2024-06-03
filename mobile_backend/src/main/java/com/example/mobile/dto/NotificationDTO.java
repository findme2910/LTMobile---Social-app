package com.example.mobile.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
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
