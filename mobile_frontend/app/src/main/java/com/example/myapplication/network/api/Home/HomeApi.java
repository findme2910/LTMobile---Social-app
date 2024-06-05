package com.example.myapplication.network.api.Home;

import com.example.myapplication.network.model.dto.HomeViewDTO;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface HomeApi {
    @GET("/user/inf")
    public Call<HomeViewDTO> getHomeView(@Header("Authorization") String token);
}
