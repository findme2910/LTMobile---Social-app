package com.example.myapplication.network.api.User;

import com.example.myapplication.network.model.dto.PostViewDTO;
import com.example.myapplication.network.model.dto.UserInformationDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface UserAPI {
    @GET("/user")
    Call<UserInformationDTO> getUserInfo(@Header("Authorization") String token);
}
