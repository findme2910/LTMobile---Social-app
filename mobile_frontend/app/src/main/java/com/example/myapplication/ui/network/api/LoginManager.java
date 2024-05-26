package com.example.myapplication.ui.network.api;

import com.example.myapplication.ui.network.model.dto.LoginRequest;
import com.example.myapplication.ui.network.model.dto.ResponseDTO;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginManager {
    public void loginUser(String username, String password, final OnLoginListener listener) {
        ApiInterface apiService = ApiClient.getRetrofitInstance().create(ApiInterface.class);
        LoginRequest loginRequest = new LoginRequest(username, password);

        Call<ResponseDTO> call = apiService.login(loginRequest);

        call.enqueue(new Callback<ResponseDTO>() {
            @Override
            public void onResponse(Call<ResponseDTO> call, Response<ResponseDTO> response) {
                if (response.isSuccessful()) {
                    String token = response.body().response;
                    if (listener != null) {
                        listener.onLoginSuccess(token);
                    }
                } else {
                    if (listener != null) {
                        listener.onLoginFailure("Đăng nhập thất bại");
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseDTO> call, Throwable t) {

                if (listener != null) {
                    listener.onLoginFailure("Lỗi mạng: " + t);
                }

            }
        });
    }

    public interface OnLoginListener {
        void onLoginSuccess(String token);

        void onLoginFailure(String errorMessage);
    }
}