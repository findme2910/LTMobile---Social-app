package com.example.myapplication.network.api.User;

import com.example.myapplication.network.api.ApiClient;
import com.example.myapplication.network.api.Friend.FriendApi;
import com.example.myapplication.network.api.HandleListener;
import com.example.myapplication.network.model.dto.IfReqAddFiendDTO;
import com.example.myapplication.network.model.dto.UserInformationDTO;
import com.example.myapplication.network.model.instance.JwtTokenManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserManager {
    public static void getProfileUser(HandleListener<UserInformationDTO> handleListener) {
        UserAPI apiService = ApiClient.getRetrofitInstance().create(UserAPI.class);
        String token = JwtTokenManager.getInstance().getToken();
        // tham số truyền vào trong call là tham số cần lấy bên server
        Call<UserInformationDTO> call = apiService.getUserInfo("Bearer " + token);

        call.enqueue(new Callback<UserInformationDTO>() {
            @Override
            public void onResponse(Call<UserInformationDTO> call, Response<UserInformationDTO> response) {
                if (response.isSuccessful() && response.body() != null) {
                    handleListener.onSuccess(response.body());
                } else {
                    handleListener.onFailure("Failed to load user profile");
                }
            }

            @Override
            public void onFailure(Call<UserInformationDTO> call, Throwable t) {
                handleListener.onFailure("Load failure: " + t.getMessage());
            }
        });
    }
}
