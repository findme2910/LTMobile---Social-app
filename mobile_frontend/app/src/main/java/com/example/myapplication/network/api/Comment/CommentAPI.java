package com.example.myapplication.network.api.Comment;

import com.example.myapplication.network.model.dto.CommentDTO;
import com.example.myapplication.network.model.dto.ResponseDTO;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface CommentAPI {
    @POST("/user/comment")
    Call<ResponseDTO> addComment(@Body CommentDTO dto, @Header("Authorization") String token);
}
