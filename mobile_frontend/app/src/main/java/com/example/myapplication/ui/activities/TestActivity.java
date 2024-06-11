package com.example.myapplication.ui.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.example.myapplication.R;
import com.example.myapplication.convert.ImageConvert;
import com.example.myapplication.ui.fragment.CommentsFragment;
import com.example.myapplication.ui.fragment.AddFriendFragment;
import com.example.myapplication.ui.fragment.HomeFragment;
import com.example.myapplication.ui.fragment.ProfileFragment;
import com.example.myapplication.ui.fragment.NotificationFragment;
import com.example.myapplication.until.DataClearer;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class TestActivity extends AppCompatActivity {
    private LinearLayout createPost;
    private ImageView userAvatar;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        final Fragment homeFragment = new HomeFragment();

        //test
        final Fragment commentFragment = new CommentsFragment();
        final Fragment notificationFragment = new NotificationFragment();

        final Fragment friendFragment = new AddFriendFragment();
        final Fragment profileFragment = new ProfileFragment();

        createPost = findViewById(R.id.create_post);
        userAvatar = findViewById(R.id.post_user_avatar);

        SharedPreferences sharedPreferences = getSharedPreferences("MyAppPrefs", MODE_PRIVATE);
        String avatarUrl = sharedPreferences.getString("avatarUrl", "");

        userAvatar.setImageBitmap(ImageConvert.base64ToBitMap(avatarUrl));


        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, homeFragment).commit();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        createPost.setOnClickListener(n -> {
            Intent intent = new Intent(getApplicationContext(), CreatePostActivity.class);
            startActivity(intent);
        });
        bottomNavigationView.setOnItemSelectedListener(item -> {
            // Xác định fragment tương ứng với mục được chọn
            if (item.getItemId() == R.id.navigation_home) {
                replaceFragment(homeFragment);
                createPost.setVisibility(View.VISIBLE);
            }

            if (item.getItemId() == R.id.navigation_notification) {
                replaceFragment(notificationFragment);
                createPost.setVisibility(View.GONE);
            }

            if (item.getItemId() == R.id.navigation_friends) {
                replaceFragment(friendFragment);
                createPost.setVisibility(View.GONE);
            }
            if (item.getItemId() == R.id.navigation_profile) {
                replaceFragment(profileFragment);
                createPost.setVisibility(View.GONE);
            }
            if (item.getItemId() == R.id.navigation_logout) {
                DataClearer dataClearer = new DataClearer(getApplicationContext());
                dataClearer.clearAllData();
                finish();
            }
            return true;
        });
    }

    public void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }
}