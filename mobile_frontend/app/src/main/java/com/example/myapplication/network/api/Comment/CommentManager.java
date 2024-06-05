package com.example.myapplication.network.api.Comment;

import com.example.myapplication.model.Comment.Comment;
import com.example.myapplication.network.api.ApiClient;
import com.example.myapplication.network.api.Friend.FriendApi;
import com.example.myapplication.network.api.HandleListener;
import com.example.myapplication.network.model.dto.CommentDTO;
import com.example.myapplication.network.model.dto.FriendRequest;
import com.example.myapplication.network.model.dto.ResponseDTO;
import com.example.myapplication.network.model.instance.JwtTokenManager;

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
}

