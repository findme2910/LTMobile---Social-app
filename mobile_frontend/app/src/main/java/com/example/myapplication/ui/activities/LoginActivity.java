package com.example.myapplication.ui.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Button btnOpenRegister = (Button) findViewById(R.id.btnOpenRegister);
        btnOpenRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doOpenRegister();
            }
        });
    }

    private void doOpenRegister() {
        Intent intent = new Intent(this, NameActivity.class);
        startActivity(intent);
    }
}