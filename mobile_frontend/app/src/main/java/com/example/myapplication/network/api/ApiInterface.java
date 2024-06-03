package com.example.myapplication.network.api;

import com.example.myapplication.network.model.dto.FriendRequest;
import com.example.myapplication.network.model.dto.IfReqAddFiendDTO;
import com.example.myapplication.network.model.dto.LoginRequest;
import com.example.myapplication.network.model.dto.RegisterRequest;
import com.example.myapplication.network.model.dto.ResponseDTO;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

import java.util.List;

public interface ApiInterface {
    @POST("/auth/login")
    Call<ResponseDTO> login(@Body LoginRequest loginRequest);

    @GET("/test")
    Call<ResponseDTO> getHello(@Header("Authorization") String token);

    @POST("/auth/register")
    Call<ResponseDTO> register(@Body RegisterRequest registerRequest);
}