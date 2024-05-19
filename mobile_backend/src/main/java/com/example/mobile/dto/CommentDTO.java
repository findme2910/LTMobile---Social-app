package com.example.mobile.dto;

import lombok.Data;

@Data
public class CommentDTO {
	private int userId;
	private int postId;
	private String content;
}
