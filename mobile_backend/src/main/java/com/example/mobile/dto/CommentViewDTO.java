package com.example.mobile.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommentViewDTO {
	private int commentId;
	private String avatar;
	private String name;
	private String content;
	private long createAt;
	private List<CommentViewDTO> replys;
}
