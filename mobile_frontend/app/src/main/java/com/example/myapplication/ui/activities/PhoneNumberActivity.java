package com.example.myapplication.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.R;

public class PhoneNumberActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone);

        Button btnNext = findViewById(R.id.btnNext);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                String name = intent.getStringExtra("name");
                String gender = intent.getStringExtra("gender");

                String phone = "123456789"; // Assume phone number input logic here

                Intent nextIntent = new Intent(PhoneNumberActivity.this, PasswordActivity.class);
                nextIntent.putExtra("name", name);
                nextIntent.putExtra("gender", gender);
                nextIntent.putExtra("phone", phone);
                // Truyền dữ liệu name, gender và phone đến activity tiếp theo
                startActivity(nextIntent);
            }
        });
    }
    }
