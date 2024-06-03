package com.example.myapplication.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

import java.util.Calendar;

public class BirthdateActivity extends AppCompatActivity {

    private EditText dateEdt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_birthdate);

        dateEdt = findViewById(R.id.date_edt);
        Button btnNext = findViewById(R.id.btnNext);
        ImageView back = findViewById(R.id.back);

        // Add TextWatcher to dateEdt
        dateEdt.addTextChangedListener(new TextWatcher() {
            private boolean isFormatting;
            private int prevLength;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (isFormatting) return;
                prevLength = s.length();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (isFormatting) return;
                isFormatting = true;

                StringBuilder sb = new StringBuilder(s);
                if (sb.length() == 2 && prevLength < sb.length()) {
                    sb.append('/');
                } else if (sb.length() == 5 && prevLength < sb.length()) {
                    sb.append('/');
                } else if (sb.length() > 10) {
                    sb.delete(10, sb.length());
                }

                dateEdt.setText(sb.toString());
                dateEdt.setSelection(sb.length());
                isFormatting = false;
            }

            @Override
            public void afterTextChanged(Editable s) {
                // No implementation needed
            }
        });

        // Handle Next button click
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String birthdateStr = dateEdt.getText().toString();
                long birthdate = convertDateToLong(birthdateStr);

                Intent intent = getIntent();
                String name = intent.getStringExtra("name");

                Intent nextIntent = new Intent(BirthdateActivity.this, GenderActivity.class);
                nextIntent.putExtra("name", name);
                nextIntent.putExtra("birthdate", birthdate);
                startActivity(nextIntent);
            }
        });

        // Handle Back button click
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private long convertDateToLong(String date) {
        try {
            String[] parts = date.split("/");
            int day = Integer.parseInt(parts[0]);
            int month = Integer.parseInt(parts[1]) - 1; // Tháng trong Calendar bắt đầu từ 0
            int year = Integer.parseInt(parts[2]);

            Calendar calendar = Calendar.getInstance();
            calendar.set(year, month, day);
            return calendar.getTimeInMillis();
        } catch (Exception e) {
            Toast.makeText(this, "Lỗi định dạng ngày", Toast.LENGTH_SHORT).show();
            return 0;
        }
    }
}
