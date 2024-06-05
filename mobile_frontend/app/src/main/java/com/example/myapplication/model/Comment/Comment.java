package com.example.myapplication.model.Comment;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
//đây là lớp cần để hiển thị lên màn hình và xử lý một số logic khi hiển thị lên màn hình
public class Comment {
    private String userName;
    private String text;
    private String avatar;
    private Date CreateAt;
    private int postID;
    private List<Comment> replies;
}
