package com.example.myapplication.ui.activities;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.network.api.RegisterManager;
import com.example.myapplication.network.model.dto.RegisterDTO;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class RegisterActivity extends AppCompatActivity {
    EditText editTextName, editTextPhone, editTextPassword, editTextRePassword, editTextBirth;
    Spinner spinnerGender;
    Button buttonRegister;

    TextView tvBackLogin;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editTextName = findViewById(R.id.et_name);
        editTextPhone = findViewById(R.id.et_phone);
        editTextPassword = findViewById(R.id.et_password);
        editTextRePassword = findViewById(R.id.et_re_password);
        editTextBirth = findViewById(R.id.et_birth);
        spinnerGender = findViewById(R.id.spinner_gender);
        buttonRegister = findViewById(R.id.joinus);
        tvBackLogin = findViewById(R.id.backLogin);
        // Thiết lập Spinner

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.gender_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGender.setAdapter(adapter);

        //Thiết lập click login
        tvBackLogin.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });
        //Thiết lập nút đăng ký
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editTextName.getText().toString().trim();
                String phone = editTextPhone.getText().toString().trim();
                String gender = spinnerGender.getSelectedItem().toString();
                String birthString = editTextBirth.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();
                String rePassword = editTextRePassword.getText().toString().trim();

                if (!password.equals(rePassword)) {
                    editTextRePassword.setError("Passwords do not match");
                    editTextRePassword.requestFocus();
                    return;
                }

                long birth = 0;
                if (!birthString.isEmpty()) {
                    try {
                        birth = parseDate(birthString);
                        if (birth > System.currentTimeMillis()) {
                            editTextBirth.setError("Birth date cannot be in the future");
                            editTextBirth.requestFocus();
                            return;
                        }
                    } catch (Exception e) {
                        editTextBirth.setError("Invalid birth date format");
                        editTextBirth.requestFocus();
                        return;
                    }
                }

                RegisterDTO registerDTO = RegisterDTO.builder()
                        .name(name)
                        .phone(phone)
                        .gender(gender)
                        .birth(birth)
                        .password(password)
                        .build();

                RegisterManager registerManager = new RegisterManager();
                registerManager.registerUser(registerDTO, new RegisterManager.OnRegisterListener() {
                    @Override
                    public void onRegisterSuccess(String message) {
                        Toast.makeText(RegisterActivity.this, "Registration successful: " + message, Toast.LENGTH_SHORT).show();
                        // Chuyển đến màn hình đăng nhập
                        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish(); // Đóng RegisterActivity để người dùng không quay lại được màn hình đăng ký bằng nút back
                    }

                    @Override
                    public void onRegisterFailure(String errorMessage) {
                        Log.e("RegisterActivity", "Registration failed: " + errorMessage);
                        Toast.makeText(RegisterActivity.this, "Registration failed: " + errorMessage, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    // Phương thức hiển thị DatePicker
    public void showDatePicker(View view) {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                editTextBirth.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
            }
        }, year, month, day);

        datePickerDialog.show();
    }
    private long parseDate(String dateString) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        Date date = sdf.parse(dateString);
        if (date != null) {
            return date.getTime();
        } else {
            throw new Exception("Invalid date");
        }
    }
}
