package com.example.myapplication.ui.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.convert.DateConvert;
import com.example.myapplication.convert.ImageConvert;
import com.example.myapplication.network.api.HandleListener;
import com.example.myapplication.network.api.Profile.ProfileManager;
import com.example.myapplication.network.model.dto.FriendViewDTO;
import com.example.myapplication.network.model.dto.ProfileDTO;
import com.example.myapplication.ui.adapters.FriendsListAdapter;
import com.example.myapplication.ui.adapters.ProfileAdapter;

import java.util.ArrayList;
import java.util.List;


public class FriendsListActivity extends AppCompatActivity {
    private RecyclerView recyclerViewAllFriendList;
    private FriendsListAdapter friendsListAdapter;
    private List<FriendViewDTO> friends;
    private ImageView backButton;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.friends_list);

        backButton = findViewById(R.id.back_arrow);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });

        friends = new ArrayList<>();
        recyclerViewAllFriendList = findViewById(R.id.list_add_friend);
        recyclerViewAllFriendList.setLayoutManager(new LinearLayoutManager(this));

    }



    @Override
    protected void onStart() {
        super.onStart();
        ProfileManager profileManager = new ProfileManager();

        // Nhận ID người dùng từ Intent
        int userId = getIntent().getIntExtra("USER_ID", -1);
        System.out.println(userId);
            profileManager.getProfileForUser(userId, new HandleListener<ProfileDTO>() {
                @Override
                public void onSuccess(ProfileDTO profileDTO) {
                    friends = profileDTO.getFriends();
                    friendsListAdapter = new FriendsListAdapter(getApplicationContext(), friends);
                    recyclerViewAllFriendList.setAdapter(friendsListAdapter);
                }

                @Override
                public void onFailure(String errorMessage) {
                    // Xử lý khi có lỗi xảy ra
                }
            });

//        }

    }
}
