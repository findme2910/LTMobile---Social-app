package com.example.myapplication.ui.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.ui.adapters.HomeAdapter;

import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class homeActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home); // Liên kết với layout home

        ImageButton homeButton = findViewById(R.id.home_button);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Thực hiện hành động khi nhấn nút "Trang chủ"
                Toast.makeText(homeActivity.this, "Home button clicked", Toast.LENGTH_SHORT).show();
            }
        });

        ImageButton addFriendButton = findViewById(R.id.addFriend_button);
        addFriendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Thực hiện hành động khi nhấn nút "Bạn bè"
                Toast.makeText(homeActivity.this, "Add Friend button clicked", Toast.LENGTH_SHORT).show();
            }
        });

        ImageButton notificationButton = findViewById(R.id.nofication_button);
        notificationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Thực hiện hành động khi nhấn nút "Thông báo"
                Toast.makeText(homeActivity.this, "Notification button clicked", Toast.LENGTH_SHORT).show();
            }
        });

        ImageButton logoutButton = findViewById(R.id.logout_button);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Thực hiện hành động khi nhấn nút "Đăng xuất"
                Toast.makeText(homeActivity.this, "Logout button clicked", Toast.LENGTH_SHORT).show();
            }
        });
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Thay thế Fragment hiện tại bằng HomeFragment
                Fragment fragment = new HomeAdapter();
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, fragment);
                fragmentTransaction.commit();
            }
        });
        }
    }



