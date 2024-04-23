package com.example.myapplication.ui.activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.myapplication.R;
import com.example.myapplication.network.api.LoginManager;
import com.example.myapplication.network.model.instance.JwtTokenManager;
import com.example.myapplication.network.test.ApiClient;

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
                        Intent i = new Intent(getApplicationContext(), HelloActivity.class);
                        startActivity(i);
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