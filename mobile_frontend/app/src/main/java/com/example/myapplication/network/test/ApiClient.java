package com.example.myapplication.network.test;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.io.IOException;

public class ApiClient {
    private static final String BASE_URL = "https://305a-14-169-61-58.ngrok-free.app/";

    public static void sendPostRequest(String endpoint, String username, String password) {
        OkHttpClient client = new OkHttpClient();

        // Xây dựng body của yêu cầu
        FormBody body = new FormBody.Builder()
                .add("username", username)
                .add("password", password)
                .build();

        // Tạo yêu cầu POST
        Request request = new Request.Builder()
                .url(BASE_URL + endpoint)
                .post(body)
                .build();

        // Gửi yêu cầu bằng cách sử dụng enqueue để thực hiện trong background
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseData = response.body().string();
                // Xử lý phản hồi từ máy chủ ở đây
            }

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                // Xử lý khi gặp lỗi kết nối hoặc xử lý
            }
        });
    }
}