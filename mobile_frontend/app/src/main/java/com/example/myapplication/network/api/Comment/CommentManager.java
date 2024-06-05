package com.example.myapplication.network.api.Comment;

import com.example.myapplication.model.Comment.Comment;
import com.example.myapplication.network.api.ApiClient;
import com.example.myapplication.network.api.Friend.FriendApi;
import com.example.myapplication.network.api.HandleListener;
import com.example.myapplication.network.model.dto.CommentDTO;
import com.example.myapplication.network.model.dto.CommentViewDTO;
import com.example.myapplication.network.model.dto.FriendRequest;
import com.example.myapplication.network.model.dto.ResponseDTO;
import com.example.myapplication.network.model.instance.JwtTokenManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommentManager {
    public void addComment(int postId, String content, HandleListener<String> handleListener) {
        CommentAPI apiService = ApiClient.getRetrofitInstance().create(CommentAPI.class);
        String token = JwtTokenManager.getInstance().getToken();
        CommentDTO commentDTO = new CommentDTO(postId, content);
        Call<ResponseDTO> call = apiService.addComment(commentDTO, "Bearer " + token);

        call.enqueue(new Callback<ResponseDTO>() {

            @Override
            public void onResponse(Call<ResponseDTO> call, Response<ResponseDTO> response) {
                if (response.isSuccessful()) {
                    handleListener.onSuccess("Success");
                } else {
                    handleListener.onFailure("Failed to add comment: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<ResponseDTO> call, Throwable t) {
                handleListener.onFailure("Failure: " + t.getMessage());
            }
        });
    }
    public static void getComment(int postId, HandleListener<List<CommentViewDTO>> handleListener) {
        CommentAPI apiService = ApiClient.getRetrofitInstance().create(CommentAPI.class);
        String token = JwtTokenManager.getInstance().getToken();
        Call<List<CommentViewDTO>> call = apiService.getComments(postId, "Bearer " + token);

        call.enqueue(new Callback<List<CommentViewDTO>>() {
            @Override
            public void onResponse(Call<List<CommentViewDTO>> call, Response<List<CommentViewDTO>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    handleListener.onSuccess(response.body());
                } else {
                    handleListener.onFailure("Failed to load comments: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<CommentViewDTO>> call, Throwable t) {
                handleListener.onFailure("Failure: " + t.getMessage());
            }
        });
    }
}

