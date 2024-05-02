package com.example.myapplication.ui.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.R;

public class name extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);

        EditText lastNameEditText = findViewById(R.id.last_name);

        lastNameEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    // Khi EditText được focus, di chuyển hint lên trên dòng văn bản nhập
                    lastNameEditText.setHint("Last Name");
                    // Hiển thị con trỏ nhấp nháy
                    lastNameEditText.setCursorVisible(true);
                } else {
                    // Khi EditText mất focus, trả lại hint vào vị trí ban đầu
                    lastNameEditText.setHint("");
                    // Ẩn con trỏ nhấp nháy
                    lastNameEditText.setCursorVisible(false);
                }
            }
        });
    }
}