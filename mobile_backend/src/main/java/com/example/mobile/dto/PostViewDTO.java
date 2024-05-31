package com.example.mobile.dto;

import lombok.Data;

@Data
public class PostViewDTO {
	private int userId;
	private int postId;
	private String avatarUser;
	private String image;
	private String content;
	private int numberLike;
	private int numberComment;
	private long createAt;
}
