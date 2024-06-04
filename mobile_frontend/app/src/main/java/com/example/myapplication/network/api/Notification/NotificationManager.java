package com.example.myapplication.network.api.Notification;

import android.util.Log;

import com.example.myapplication.network.api.ApiClient;
import com.example.myapplication.network.api.Friend.FriendApi;
import com.example.myapplication.network.api.HandleListener;
import com.example.myapplication.network.model.dto.FriendRequest;
import com.example.myapplication.network.model.dto.NotificationDTO;
import com.example.myapplication.network.model.dto.RequestNotificationDTO;
import com.example.myapplication.network.model.dto.ResponseDTO;
import com.example.myapplication.network.model.instance.JwtTokenManager;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationManager {
    public void requestNotification(RequestNotificationDTO dto, HandleListener<List<NotificationDTO>> handleListener) {
        NotificationApi apiService = ApiClient.getRetrofitInstance().create(NotificationApi.class);
        // Lấy ra token của người dùng
        String token = JwtTokenManager.getInstance().getToken();
        Map<String, String> options = dto.toMap();

        Call<List<NotificationDTO>> call = apiService.getNotification(options, "Bearer " + token);
        Log.d("NotificationManager", "Requesting notifications with next: " + options);
        call.enqueue(new Callback<List<NotificationDTO>>() {
            @Override
            public void onResponse(Call<List<NotificationDTO>> call, Response<List<NotificationDTO>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    handleListener.onSuccess(response.body());
                } else {
                    handleListener.onFailure("Failed to load notifications: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<NotificationDTO>> call, Throwable t) {
                handleListener.onFailure("Failure: " + t.getMessage());
            }
        });
    }

}
