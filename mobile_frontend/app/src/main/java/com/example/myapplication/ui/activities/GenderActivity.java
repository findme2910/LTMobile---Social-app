package com.example.myapplication.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.R;

public class GenderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gender);

        Button btnNext = findViewById(R.id.btnNext);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioGroup radioGroupGender = findViewById(R.id.radioGroupGender);
                int selectedId = radioGroupGender.getCheckedRadioButtonId();
                String gender;
                if (selectedId == R.id.radioButtonMale) {
                    gender = "MALE";
                } else if (selectedId == R.id.radioButtonFemale) {
                    gender = "FEMALE";
                } else {
                    // Handle the case where no RadioButton is selected
                    // For example, display an error message or set a default value
                    gender = "Không xác định";
                }

                Intent intent = getIntent();
                String name = intent.getStringExtra("name");

                Intent nextIntent = new Intent(GenderActivity.this, PhoneNumberActivity.class);
                nextIntent.putExtra("name", name);
                nextIntent.putExtra("gender", gender);
                startActivity(nextIntent);
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

        ImageView back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    // Bỏ phần này vì chưa được sử dụng trong code hiện tại
    /*
    private void doOpenLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
    */
}
