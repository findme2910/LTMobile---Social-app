package com.example.mobile.dto;

import lombok.Data;

@Data
public class AddPostDTO {
	private String content;
	private int userId;
	private String image;
}
