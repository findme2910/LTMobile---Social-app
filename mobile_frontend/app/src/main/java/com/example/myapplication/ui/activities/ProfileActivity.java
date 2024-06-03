package com.example.myapplication.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.Friend;
import com.example.myapplication.model.Post;
import com.example.myapplication.network.api.HandleListener;
import com.example.myapplication.network.api.Profile.ProfileManager;
import com.example.myapplication.network.model.dto.ProfileDTO;
import com.example.myapplication.ui.adapters.PostAdapter;
import com.example.myapplication.ui.adapters.ProfileAdapter;

import java.util.ArrayList;
import java.util.List;

public class ProfileActivity extends AppCompatActivity {

    private RecyclerView recyclerView, postsRecyclerView;
    private ProfileAdapter profileAdapter;
    private PostAdapter postAdapter;
    private List<Friend> friends;
    private List<Post> posts;

    private TextView countFriend;
    private TextView profileName, profileAddress, profileBirthday;
    private Button editProfileButton, friendsListButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

//        // Tạo dữ liệu giả
//        User user = new User(1, "Nguyễn Hoài Thương", "", "Long An", "26/09/2003");
//
        // Liên kết với các TextView trong giao diện
        profileName = findViewById(R.id.profile_name);
        profileAddress = findViewById(R.id.profile_address);
        profileBirthday = findViewById(R.id.profile_birthDay);
//
//        // Thiết lập dữ liệu giả lên giao diện
//        profileName.setText(user.getName());
//        profileAddress.setText(user.getAddress());
//        profileBirthday.setText(user.getBirthDay());


        friends = new ArrayList<>();
        friends.add(new Friend(1, "Hoai Thuong", "base64_string_of_avatar"));
        friends.add(new Friend(2, "Quang Huy", "base64_string_of_avatar"));
        friends.add(new Friend(3, "Thai Son", "base64_string_of_avatar"));
        friends.add(new Friend(4, "Huu Nhan", "base64_string_of_avatar"));
        friends.add(new Friend(5, "Hoai Nam", "base64_string_of_avatar"));

        // Thêm dữ liệu giả vào RecyclerView
        recyclerView = findViewById(R.id.profile_friend_list);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        profileAdapter = new ProfileAdapter(this, friends);
        recyclerView.setAdapter(profileAdapter);

        // Khởi tạo TextView và thiết lập số lượng bạn bè
        countFriend = findViewById(R.id.numberOfFriends);
        countFriend.setText(String.valueOf(friends.size()));


        posts = new ArrayList<>();
        posts.add(new Post(1, "Hoai Thuong", "hello", "", "", "", ""));
        posts.add(new Post(2, "Hoai Thuong", "alo 12344", "", "", "", ""));
        posts.add(new Post(3, "Hoai Thuong", " hello hello hello hello hello vhello" +
                "hellohellohellohellohellohellovhellohellohellohellohellohellohellohellovhellohellohellohellohellohellohellohellovhellohellohellohellohellohe" +
                "llohellohellovhellohellohellohellohellohellohellohellovhellohello", "", "", "", ""));
        // Thêm dữ liệu bài post vào RecyclerView
        postsRecyclerView = findViewById(R.id.post_list);
        postsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        postAdapter = new PostAdapter(this, posts);
        postsRecyclerView.setAdapter(postAdapter);


        editProfileButton = findViewById((R.id.edit_profile_button));

        editProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                i.setClass(ProfileActivity.this, ProfileEditActivity.class);
                startActivity(i);
            }
        });

        friendsListButton = findViewById((R.id.all_friends_button));

        friendsListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                i.setClass(ProfileActivity.this, FriendsListActivity.class);
                startActivity(i);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        ProfileManager profileManager = new ProfileManager();
        profileManager.getProfile(new HandleListener<ProfileDTO>() {
            @Override
            public void onSuccess(ProfileDTO profileDTO) {
                profileName.setText(profileDTO.getName());
                profileBirthday.setText(profileDTO.getBirth());

            }

            @Override
            public void onFailure(String errorMessage) {

            }
        });
    }




}
