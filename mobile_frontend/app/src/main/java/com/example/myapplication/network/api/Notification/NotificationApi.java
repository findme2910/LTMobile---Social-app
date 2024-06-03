package com.example.myapplication.network.api.Notification;

import com.example.myapplication.network.model.dto.IfReqAddFiendDTO;
import com.example.myapplication.network.model.dto.NotificationDTO;
import com.example.myapplication.network.model.dto.RequestNotificationDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface NotificationApi {
    @GET("/noti")
    Call<List<NotificationDTO>> getNotification(@Query("next") RequestNotificationDTO requestNotificationDTO, @Header("Authorization") String token);
}
