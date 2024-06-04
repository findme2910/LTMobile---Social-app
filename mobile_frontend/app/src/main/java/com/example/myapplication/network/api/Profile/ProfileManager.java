package com.example.myapplication.network.api.Profile;

import com.example.myapplication.network.api.ApiClient;
import com.example.myapplication.network.api.HandleListener;
import com.example.myapplication.network.model.dto.ProfileDTO;
import com.example.myapplication.network.model.dto.ResponseDTO;
import com.example.myapplication.network.model.dto.UpdateAvatarDTO;
import com.example.myapplication.network.model.instance.JwtTokenManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProfileManager {
    public void getProfile(HandleListener<ProfileDTO> handleListener) {
        ProfileApi profileApi = ApiClient.getRetrofitInstance().create(ProfileApi.class);
        String token = JwtTokenManager.getInstance().getToken();
        Call<ProfileDTO> call = profileApi.getProfile("Bearer " + token);

        call.enqueue(new Callback<ProfileDTO>() {
            @Override
            public void onResponse(Call<ProfileDTO> call, Response<ProfileDTO> response) {
                handleListener.onSuccess(response.body());

            }

            @Override
            public void onFailure(Call<ProfileDTO> call, Throwable t) {
                handleListener.onFailure("load failure");
            }
        });
    }
    public void updateAvatar(UpdateAvatarDTO updateAvatarDTO, HandleListener<String> handleListener) {

    }
}
