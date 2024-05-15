package com.example.myapplication.network.api.Friend;

import com.example.myapplication.network.api.ApiClient;
import com.example.myapplication.network.api.ApiInterface;
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
        ApiInterface apiService = ApiClient.getRetrofitInstance().create(ApiInterface.class);
//        String token = JwtTokenManager.getInstance().getToken();
        Call<List<IfReqAddFiendDTO>> call = apiService.getAddFriend("Bearer " + "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIwODU0NzA1OTMyIiwiaWF0IjoxNzE1NjU3NTA1LCJleHAiOjE3MTU2OTM1MDV9.pbV6R_O73wtHD3pSGn5wc2xk9ZtVln51yEuUqX0GiVZEeqnfgeGtq1WMM5tOKj11faaT8k-ychtSgdUs-L6C2g");

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

    public void acceptAddFriend(int userId, HandleListener<String> handleListener) {
        ApiInterface apiService = ApiClient.getRetrofitInstance().create(ApiInterface.class);
//        String token = JwtTokenManager.getInstance().getToken();
        Call<ResponseDTO> call = apiService.acceptAddFriend(FriendRequest.builder().toUserId(userId).build(), "Bearer " + "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIwODU0NzA1OTMyIiwiaWF0IjoxNzE1NjU3NTA1LCJleHAiOjE3MTU2OTM1MDV9.pbV6R_O73wtHD3pSGn5wc2xk9ZtVln51yEuUqX0GiVZEeqnfgeGtq1WMM5tOKj11faaT8k-ychtSgdUs-L6C2g");

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
