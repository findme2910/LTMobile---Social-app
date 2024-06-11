package com.example.myapplication.ui.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.myapplication.R;
import com.example.myapplication.network.api.HandleListener;
import com.example.myapplication.network.api.LoginManager;
import com.example.myapplication.network.api.Profile.ProfileManager;
import com.example.myapplication.network.model.dto.UserInformationDTO;
import com.example.myapplication.network.model.instance.JwtTokenManager;

public class MainActivity extends AppCompatActivity {
    RelativeLayout buttonLogin;
    EditText editTextUsername, editTextPassword;

    int userId;

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

                        ProfileManager profileManager = new ProfileManager();
                        profileManager.getUserInfor(new HandleListener<UserInformationDTO>() {
                            @Override
                            public void onSuccess(UserInformationDTO userInformationDTO) {
                                userId = userInformationDTO.getUserId();
                                // Chuyển hướng sang TestActivity
                                Intent i = new Intent(getApplicationContext(), TestActivity.class);
                                i.putExtra("LOGGED_USER_ID", userId); // Đặt giá trị userId vào Intent
                                startActivity(i);
                            }

                            @Override
                            public void onFailure(String errorMessage) {
                                System.out.println("Get id fail!");
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

//0854705932