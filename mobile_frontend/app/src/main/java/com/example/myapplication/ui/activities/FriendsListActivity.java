package com.example.myapplication.ui.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.Friend;
import com.example.myapplication.model.FriendRequestModel;
import com.example.myapplication.ui.adapters.FriendsListAdapter;

import java.util.ArrayList;
import java.util.List;


public class FriendsListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private FriendsListAdapter friendsListAdapter;
    private List<Friend> friends;
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

        friends.add(new Friend(1, "Hoai Thuong", "base64_string_of_avatar"));
        friends.add(new Friend(2, "Quang Huy", "base64_string_of_avatar"));
        friends.add(new Friend(3, "Thai Son", "base64_string_of_avatar"));
        friends.add(new Friend(4, "Huu Nhan", "base64_string_of_avatar"));
        friends.add(new Friend(5, "Hoai Nam", "base64_string_of_avatar"));

        // Thêm dữ liệu giả vào RecyclerView
        recyclerView = findViewById(R.id.list_add_friend);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        friendsListAdapter = new FriendsListAdapter(this, friends);
        recyclerView.setAdapter(friendsListAdapter);






    }
}
