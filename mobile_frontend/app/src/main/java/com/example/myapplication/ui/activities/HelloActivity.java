package com.example.myapplication.ui.activities;

import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.myapplication.R;
import com.example.myapplication.network.api.HelloManager;

public class HelloActivity extends AppCompatActivity {
    TextView helloText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello);

        helloText = findViewById(R.id.hello);
    }

    @Override
    protected void onStart() {
        super.onStart();
        HelloManager helloManager = new HelloManager();
        helloManager.getHello(new HelloManager.OnHelloListener() {
            @Override
            public void onLoginSuccess(String hello) {
                helloText.setText(hello);
            }

            @Override
            public void onLoginFailure(String errorMessage) {
                Toast.makeText(getApplicationContext(),errorMessage,Toast.LENGTH_LONG).show();
            }
        });

    }
}