package com.example.myapplication.ui.activities;

import static com.example.myapplication.R.*;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
<<<<<<< Updated upstream

//import androidx.activity.EdgeToEdge;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.graphics.Insets;
//import androidx.core.view.ViewCompat;
//import androidx.core.view.WindowInsetsCompat;

import androidx.appcompat.app.AppCompatActivity;
=======

//import androidx.activity.EdgeToEdge;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.graphics.Insets;
//import androidx.core.view.ViewCompat;
//import androidx.core.view.WindowInsetsCompat;
>>>>>>> Stashed changes

import com.example.myapplication.R;

public class name extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);

<<<<<<< Updated upstream
        Button btnNext = (Button) findViewById(id.btnNext);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                doOpenBirthdate();
            }
        });


        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView accExist = (TextView) findViewById(R.id.accountExist);
        accExist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doOpenLogin();
=======
        Button btnNext = (Button) findViewById(R.id.btnNext);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                EditText et_lName = (EditText) findViewById(id.last_name);
//                EditText et_fName = (EditText) findViewById(id.first_name);
//                String input_LName = et_lName.getText().toString();
//                String input_fName = et_fName.getText().toString();
//                if (input_LName.length() == 0 && input_fName.length() == 0) {
//                    TextView error = (TextView) findViewById(id.error);
//                    et_lName.setBackgroundResource(drawable.err_rounded_border);
//                    et_fName.setBackgroundResource(drawable.err_rounded_border);
//                    error.setText("Vui lòng nhập họ và tên của bạn.");
//                    error.setVisibility(View.VISIBLE);
//                }
//                else if (input_LName.length() == 0) {
//                    TextView error = (TextView) findViewById(id.error);
//                    et_lName.setBackgroundResource(drawable.err_rounded_border);
//                    error.setText("Họ không thể để trống.");
//                    error.setVisibility(View.VISIBLE);
//                }
//                else if (input_fName.length() == 0) {
//                    TextView error = (TextView) findViewById(id.error);
//                    et_fName.setBackgroundResource(drawable.err_rounded_border);
//                    error.setText("Tên không thể để trống.");
//                    error.setVisibility(View.VISIBLE);
//                }
                 doOpenBirthdate();
>>>>>>> Stashed changes
            }
        });


        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView accExist = (TextView) findViewById(R.id.accountExist);
        accExist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doOpenLogin();
            }
        });
        }

    private void doOpenBirthdate() {
        Intent intent = new Intent(this, birthdate.class);
        startActivity(intent);
    }

    private void doOpenLogin() {
        Intent intent = new Intent(this, login.class);
        startActivity(intent);
    }

    private void doOpenBirthdate() {
        Intent intent = new Intent(this, birthdate.class);
        startActivity(intent);
    }

    private void doOpenLogin() {
        Intent intent = new Intent(this, login.class);
        startActivity(intent);
    }
}