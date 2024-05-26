package com.example.myapplication.network.api.Friend;

import com.example.myapplication.network.model.dto.FriendRequest;
import com.example.myapplication.network.model.dto.IfReqAddFiendDTO;
import com.example.myapplication.network.model.dto.ResponseDTO;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

import java.util.List;

public interface FriendApi {
    @GET("/friend/addfriend")
    Call<List<IfReqAddFiendDTO>> getAddFriend(@Header("Authorization") String token);
    @GET("/friend/suggest")
    Call<List<IfReqAddFiendDTO>> getSuggestAddFriend(@Header("Authorization") String token);
    @POST("/friend/addfriend")
    Call<ResponseDTO> requestAddFriend(@Body FriendRequest friendRequest, @Header("Authorization") String token);

    @POST("/friend/reject/addfriend")
    Call<ResponseDTO> rejectAddFriend(@Body FriendRequest friendRequest, @Header("Authorization") String token);
    @POST("/friend/accept/addfriend")
    Call<ResponseDTO> acceptAddFriend(@Body FriendRequest friendRequest, @Header("Authorization") String token);

    @POST("/friend/cancel/addfriend")
    Call<ResponseDTO> cancelAddFriend(@Body FriendRequest friendRequest, @Header("Authorization") String token);

}
