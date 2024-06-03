package com.example.myapplication.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class NameActivity extends AppCompatActivity {

    private EditText edtFirstName, edtLastName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);

        edtFirstName = findViewById(R.id.first_name);
        edtLastName = findViewById(R.id.last_name);
        Button btnNext = findViewById(R.id.btnNext);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstName = edtFirstName.getText().toString();
                String lastName = edtLastName.getText().toString();

                // Ghép first name và last name thành một name duy nhất
                String name = firstName + " " + lastName;

                Intent intent = new Intent(NameActivity.this, BirthdateActivity.class);
                intent.putExtra("name", name);
                startActivity(intent);
            }
        });

        // Bỏ phần này vì chưa được sử dụng trong code hiện tại
        /*
        TextView accExist = findViewById(R.id.accountExist);
        accExist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doOpenLogin();
            }
        });
        */
    }

    // Bỏ phần này vì chưa được sử dụng trong code hiện tại
    /*
    private void doOpenLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
    */
}
