package com.example.myapplication.ui.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.myapplication.R;
import com.example.myapplication.network.api.HandleListener;
import com.example.myapplication.network.api.LoginManager;
import com.example.myapplication.network.api.User.UserManager;
import com.example.myapplication.network.model.dto.UserInformationDTO;
import com.example.myapplication.network.model.instance.JwtTokenManager;

public class MainActivity extends AppCompatActivity {
    RelativeLayout buttonLogin;
    EditText editTextUsername, editTextPassword;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextUsername = findViewById(R.id.et_email);
        editTextPassword = findViewById(R.id.et_password);
        buttonLogin = findViewById(R.id.log_in_button);

        buttonLogin.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                String username = editTextUsername.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();

                LoginManager loginManager = new LoginManager();
                loginManager.loginUser(username, password, new LoginManager.OnLoginListener() {
                    @Override
                    public void onLoginSuccess(String token) {
                        // Lấy SharedPreferences
                        JwtTokenManager.getInstance().setToken(token);

                        // Gọi API lấy ra thông tin người dùng
                        UserManager.getProfileUser(new HandleListener<UserInformationDTO>() {
                            @Override
                            public void onSuccess(UserInformationDTO userInformationDTO) {
                                // Lưu thông tin người dùng vào sharedPreferences
                                SharedPreferences sharedPreferences = getSharedPreferences("MyAppPrefs", MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString("username", userInformationDTO.getName());
                                editor.putString("avatarUrl", userInformationDTO.getAvatar());
                                editor.putInt("iduser", userInformationDTO.getUserId());
                                editor.apply();

                                //Chuyển sang homeActivity
                                Intent i = new Intent(getApplicationContext(), TestActivity.class);
                                startActivity(i);
                            }

                            @Override
                            public void onFailure(String errorMessage) {
                                Log.e("UserInfo", "Error: " + errorMessage);
                            }
                        });




                    }
                    @Override
                    public void onLoginFailure(String errorMessage) {
                        System.out.println(errorMessage);
                    }
                });
            }
        });
    }
}