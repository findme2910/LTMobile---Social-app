package com.example.myapplication.network.model.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
// Đây là lớp DTO dùng để lấy get các comment trong một bài viết
@Data
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CommentViewDTO {
    private int commentId;
    private String avatar;
    private String name;
    private String content;
    private long createAt;
    private List<CommentViewDTO> replys;
}
