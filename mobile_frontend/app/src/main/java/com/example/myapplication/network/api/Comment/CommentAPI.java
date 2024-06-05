package com.example.myapplication.network.api.Comment;

import com.example.myapplication.network.model.dto.CommentDTO;
import com.example.myapplication.network.model.dto.CommentViewDTO;
import com.example.myapplication.network.model.dto.ResponseDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface CommentAPI {
    @POST("/user/comment")
    Call<ResponseDTO> addComment(@Body CommentDTO dto, @Header("Authorization") String token);
    @GET("/post/comment/{postId}")
    Call<List<CommentViewDTO>> getComments(@Path("postId") int postId, @Header("Authorization") String token);
}
