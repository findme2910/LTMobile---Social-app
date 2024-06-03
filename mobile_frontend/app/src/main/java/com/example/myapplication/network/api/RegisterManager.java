package com.example.myapplication.network.api;

import com.example.myapplication.network.api.ApiClient;
import com.example.myapplication.network.api.ApiInterface;
import com.example.myapplication.network.model.dto.RegisterRequest;
import com.example.myapplication.network.model.dto.ResponseDTO;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterManager {

    public void registerUser(String name, String phone, String gender, long birth,  String password, final OnRegisterListener listener) {
        ApiInterface apiService = ApiClient.getRetrofitInstance().create(ApiInterface.class);
        RegisterRequest registerRequest = new RegisterRequest(name, phone, gender, birth, password);

        Call<ResponseDTO> call = apiService.register(registerRequest);

        call.enqueue(new Callback<ResponseDTO>() {
            @Override
            public void onResponse(Call<ResponseDTO> call, Response<ResponseDTO> response) {
                if (response.isSuccessful()) {
                    String message = response.body().response;
                    if (listener != null) {
                        listener.onRegisterSuccess(message);
                    }
                } else {
                    if (listener != null) {
                        listener.onRegisterFailure("Đăng ký thất bại");
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseDTO> call, Throwable t) {
                if (listener != null) {
                    listener.onRegisterFailure("Lỗi mạng: " + t);
                }
            }
        });
    }

    public interface OnRegisterListener {
        void onRegisterSuccess(String message);

        void onRegisterFailure(String errorMessage);
    }
}
