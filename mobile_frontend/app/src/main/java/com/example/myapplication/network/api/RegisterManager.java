package com.example.myapplication.network.api;

import android.util.Log;

import com.example.myapplication.network.model.dto.RegisterDTO;
import com.example.myapplication.network.model.dto.ResponseDTO;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterManager {
    public interface OnRegisterListener {
        void onRegisterSuccess(String message);
        void onRegisterFailure(String errorMessage);
    }

    public void registerUser(RegisterDTO registerDTO, final OnRegisterListener listener) {
        ApiInterface apiService = ApiClient.getRetrofitInstance().create(ApiInterface.class);
        Call<ResponseDTO> call = apiService.register(registerDTO);

        call.enqueue(new Callback<ResponseDTO>() {
            @Override
            public void onResponse(Call<ResponseDTO> call, Response<ResponseDTO> response) {
                if (response.isSuccessful()) {
                    if (listener != null) {
                        listener.onRegisterSuccess(response.body().getResponse());
                    }
                } else {
                    if (listener != null) {
                        String errorMessage = "Registration failed";
                        if (response.errorBody() != null) {
                            try {
                                errorMessage = response.errorBody().string();
                                Log.e("RegisterError", "Error body: " + errorMessage);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }else{
                            Log.e("RegisterError", "Response code: " + response.code());
                        }
                        listener.onRegisterFailure(errorMessage);
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseDTO> call, Throwable t) {
                if (listener != null) {
                    listener.onRegisterFailure("Network error: " + t.getMessage());
                }
            }
        });
    }
}

