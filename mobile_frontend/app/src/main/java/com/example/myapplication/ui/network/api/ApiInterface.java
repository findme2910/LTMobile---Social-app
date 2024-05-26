package com.example.myapplication.ui.network.api;

import com.example.myapplication.ui.network.model.dto.LoginRequest;
import com.example.myapplication.ui.network.model.dto.ResponseDTO;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiInterface {
    @POST("/auth/login")
    Call<ResponseDTO> login(@Body LoginRequest loginRequest);

    @GET("/test")
    Call<ResponseDTO> getHello(@Header("Authorization") String token);



}