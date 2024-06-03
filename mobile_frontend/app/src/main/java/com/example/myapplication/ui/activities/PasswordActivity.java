package com.example.myapplication.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
//import com.example.myapplication.network.api.RegisterManager;

public class PasswordActivity extends AppCompatActivity {

    private EditText etPassword;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);

        etPassword = findViewById(R.id.et_password);
        btnRegister = findViewById(R.id.btnRegister);

        // Lấy dữ liệu từ Intent trước đó
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String name = extras.getString("name");
            long birthdate = extras.getLong("birthdate");
            String gender = extras.getString("gender");
            String phone = extras.getString("phone");

            // Sử dụng dữ liệu ở đây nếu cần
        }

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
    }

    private void registerUser() {
        String password = etPassword.getText().toString().trim();

        // Kiểm tra xem mật khẩu có hợp lệ không
        if (password.isEmpty()) {
            etPassword.setError("Vui lòng nhập mật khẩu");
            etPassword.requestFocus();
            return;
        }

        // Lấy dữ liệu từ Intent trước đó (nếu cần)
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String name = extras.getString("name");
            long birthdate = extras.getLong("birthdate");
            String gender = extras.getString("gender");
            String phone = extras.getString("phone");

//            // Thực hiện đăng ký người dùng bằng dữ liệu này
//            RegisterManager registerManager = new RegisterManager();
//            registerManager.registerUser(name, phone, gender, birthdate, password, new RegisterManager.OnRegisterListener() {
//                @Override
//                public void onRegisterSuccess(String message) {
//                    // Xử lý khi đăng ký thành công
//                    Toast.makeText(PasswordActivity.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
//
//                    // Chuyển sang màn hình chính hoặc màn hình tiếp theo
//                    // Ví dụ: Chuyển sang màn hình đăng nhập
//                    Intent intent = new Intent(PasswordActivity.this, LoginActivity.class);
//                    startActivity(intent);
//                    finish();
//                }
//
//                @Override
//                public void onRegisterFailure(String errorMessage) {
//                    // Xử lý khi đăng ký thất bại
//                    Toast.makeText(PasswordActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(PasswordActivity.this, NameActivity.class);
//                    startActivity(intent);
//                }
//            });
        }
    }
}
