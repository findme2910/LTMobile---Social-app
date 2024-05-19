package com.example.myapplication.network.api.Friend;

import com.example.myapplication.network.api.ApiClient;
import com.example.myapplication.network.api.HandleListener;
import com.example.myapplication.network.model.dto.FriendRequest;
import com.example.myapplication.network.model.dto.IfReqAddFiendDTO;
import com.example.myapplication.network.model.dto.ResponseDTO;
import com.example.myapplication.network.model.instance.JwtTokenManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

public class FriendAddManager {
    public void getFriendAdds(HandleListener<List<IfReqAddFiendDTO>> handleListener) {
        FriendApi apiService = ApiClient.getRetrofitInstance().create(FriendApi.class);
        String token = JwtTokenManager.getInstance().getToken();
        Call<List<IfReqAddFiendDTO>> call = apiService.getAddFriend("Bearer " + token);

        call.enqueue(new Callback<List<IfReqAddFiendDTO>>() {
            @Override
            public void onResponse(Call<List<IfReqAddFiendDTO>> call, Response<List<IfReqAddFiendDTO>> response) {
                handleListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<List<IfReqAddFiendDTO>> call, Throwable t) {
                handleListener.onFailure("load failure");
            }
        });
    }

    public void getSuggestFriendAdds(HandleListener<List<IfReqAddFiendDTO>> handleListener) {
        FriendApi apiService = ApiClient.getRetrofitInstance().create(FriendApi.class);
        String token = JwtTokenManager.getInstance().getToken();
        Call<List<IfReqAddFiendDTO>> call = apiService.getSuggestAddFriend("Bearer " + token);

        call.enqueue(new Callback<List<IfReqAddFiendDTO>>() {
            @Override
            public void onResponse(Call<List<IfReqAddFiendDTO>> call, Response<List<IfReqAddFiendDTO>> response) {
                handleListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<List<IfReqAddFiendDTO>> call, Throwable t) {
                handleListener.onFailure("load failure");
            }
        });
    }

    public void requestAddFriend(int userId, HandleListener<String> handleListener) {
        FriendApi apiService = ApiClient.getRetrofitInstance().create(FriendApi.class);
        String token = JwtTokenManager.getInstance().getToken();
        Call<ResponseDTO> call = apiService.requestAddFriend(FriendRequest.builder().userId(userId).build(), "Bearer " + token);

        call.enqueue(new Callback<ResponseDTO>() {

            @Override
            public void onResponse(Call<ResponseDTO> call, Response<ResponseDTO> response) {
                handleListener.onSuccess("Success");
            }

            @Override
            public void onFailure(Call<ResponseDTO> call, Throwable t) {
                handleListener.onSuccess("Failure");
            }
        });
    }

    public void acceptAddFriend(int userId, HandleListener<String> handleListener) {
        FriendApi apiService = ApiClient.getRetrofitInstance().create(FriendApi.class);
        String token = JwtTokenManager.getInstance().getToken();
        Call<ResponseDTO> call = apiService.acceptAddFriend(FriendRequest.builder().userId(userId).build(), "Bearer " + token);

        call.enqueue(new Callback<ResponseDTO>() {

            @Override
            public void onResponse(Call<ResponseDTO> call, Response<ResponseDTO> response) {
                handleListener.onSuccess("Success");
            }

            @Override
            public void onFailure(Call<ResponseDTO> call, Throwable t) {
                handleListener.onSuccess("Failure");
            }
        });
    }

    public void rejectAddFriend(int userId, HandleListener<String> handleListener) {
        FriendApi apiService = ApiClient.getRetrofitInstance().create(FriendApi.class);
        String token = JwtTokenManager.getInstance().getToken();
        Call<ResponseDTO> call = apiService.rejectAddFriend(FriendRequest.builder().userId(userId).build(), "Bearer " + token);

        call.enqueue(new Callback<ResponseDTO>() {

            @Override
            public void onResponse(Call<ResponseDTO> call, Response<ResponseDTO> response) {
                handleListener.onSuccess("Success");
            }

            @Override
            public void onFailure(Call<ResponseDTO> call, Throwable t) {
                handleListener.onSuccess("Failure");
            }
        });
    }

    public void cancelAddFriend(int userId, HandleListener<String> handleListener) {
        FriendApi apiService = ApiClient.getRetrofitInstance().create(FriendApi.class);
        String token = JwtTokenManager.getInstance().getToken();
        Call<ResponseDTO> call = apiService.cancelAddFriend(FriendRequest.builder().userId(userId).build(), "Bearer " + token);

        call.enqueue(new Callback<ResponseDTO>() {

            @Override
            public void onResponse(Call<ResponseDTO> call, Response<ResponseDTO> response) {
                handleListener.onSuccess("Success");
            }

            @Override
            public void onFailure(Call<ResponseDTO> call, Throwable t) {
                handleListener.onSuccess("Failure");
            }
        });
    }
}
