package com.example.myapplication.ui.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

//import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.R;

public class password extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_password);

        TextView accExist = (TextView) findViewById(R.id.accountExist);
        accExist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doOpenLogin();
            }
        });

        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Button btnRegister = (Button) findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                @SuppressLint({"MissingInflatedId", "LocalSuppress"}) EditText et_password = (EditText) findViewById(R.id.et_password);
                String input = et_password.getText().toString();
                if (input.length() < 6) {
                    et_password.setBackgroundResource(R.drawable.err_rounded_border);
                    TextView error = (TextView) findViewById(R.id.error);
                    error.setVisibility(View.VISIBLE);
                    if (input.length() == 0) error.setText("Không được để trống mật khẩu.");
                    else error.setText("Mật khẩu này quá ngắn.");
                }
            }
        });

        ImageView back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void doOpenLogin() {
        Intent intent = new Intent(this, login.class);
        startActivity(intent);
    }
}
