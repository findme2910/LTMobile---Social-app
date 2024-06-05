package com.example.myapplication.network.api.Home;

import com.example.myapplication.network.api.ApiClient;
import com.example.myapplication.network.api.Friend.FriendApi;
import com.example.myapplication.network.api.HandleListener;
import com.example.myapplication.network.model.dto.HomeViewDTO;
import com.example.myapplication.network.model.dto.IfReqAddFiendDTO;
import com.example.myapplication.network.model.instance.JwtTokenManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

public class HomeManager {
    public void getHomeView(HandleListener<HomeViewDTO> handleListener) {
        HomeApi apiService = ApiClient.getRetrofitInstance().create(HomeApi.class);
        String token = JwtTokenManager.getInstance().getToken();
        Call<HomeViewDTO> call = apiService.getHomeView("Bearer " + token);

        call.enqueue(new Callback<HomeViewDTO>() {
            @Override
            public void onResponse(Call<HomeViewDTO> call, Response<HomeViewDTO> response) {
                handleListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<HomeViewDTO> call, Throwable t) {

            }
        });
    }
}
