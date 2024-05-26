package com.example.myapplication.ui.network.api;

import com.example.myapplication.ui.network.model.dto.ResponseDTO;
import com.example.myapplication.ui.network.model.instance.JwtTokenManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HelloManager {
    public void getHello(OnHelloListener listener){
        ApiInterface apiService = ApiClient.getRetrofitInstance().create(ApiInterface.class);
        String token = JwtTokenManager.getInstance().getToken();
        Call<ResponseDTO> call = apiService.getHello("Bearer " + token);

        call.enqueue(new Callback<ResponseDTO>() {

            @Override
            public void onResponse(Call<ResponseDTO> call, Response<ResponseDTO> response) {
                    listener.onLoginSuccess(response.body().response);
            }

            @Override
            public void onFailure(Call<ResponseDTO> call, Throwable t) {
                    listener.onLoginFailure(t.getMessage());
            }
        });
    }
    public interface OnHelloListener {
        void onLoginSuccess(String hello);

        void onLoginFailure(String errorMessage);
    }
}

