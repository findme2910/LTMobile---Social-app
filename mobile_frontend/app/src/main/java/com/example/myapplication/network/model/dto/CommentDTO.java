package com.example.myapplication.network.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//các đối tượng dto để chuyển dữ liệu từ server sang client thì phải có thuộc tính giống nhau
@Data
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {
    private int postId;
    private String content;
}
