package com.example.myapplication.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.convert.DateConvert;
import com.example.myapplication.convert.DateConvertProfile;
import com.example.myapplication.convert.ImageConvert;
import com.example.myapplication.model.Post;
import com.example.myapplication.network.api.HandleListener;
import com.example.myapplication.network.api.Profile.ProfileManager;
import com.example.myapplication.network.model.dto.FriendViewDTO;
import com.example.myapplication.network.model.dto.ProfileDTO;
import com.example.myapplication.ui.adapters.PostAdapter;
import com.example.myapplication.ui.adapters.ProfileAdapter;

import java.util.ArrayList;
import java.util.List;

public class ProfileActivity extends AppCompatActivity {

    private RecyclerView recyclerViewFriendList, postsRecyclerView;
    private ProfileAdapter profileAdapter;
    private PostAdapter postAdapter;

    private List<Post> posts;

    private List<FriendViewDTO> friends;

    private TextView countFriend;
    private TextView profileName, profileBirthday;

    private ImageView userAvatar;
    private Button editProfileButton, friendsListButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        // Liên kết với các TextView trong giao diện
        profileName = findViewById(R.id.profile_name);
        profileBirthday = findViewById(R.id.profile_birthDay);
        userAvatar = findViewById(R.id.profile_photo);

        countFriend = findViewById(R.id.numberOfFriends);

        friends = new ArrayList<>();

//        RecyclerView friend list
        recyclerViewFriendList = findViewById(R.id.profile_friend_list);
        recyclerViewFriendList.setLayoutManager(new GridLayoutManager(this, 3));


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
                profileBirthday.setText(DateConvertProfile.convertToString(profileDTO.getBirth()));
                userAvatar.setImageBitmap(ImageConvert.base64ToBitMap(profileDTO.getAvatar()));


                friends = profileDTO.getFriends();

//                Lấy 6 bạn đầu tiên
                List<FriendViewDTO> firstSixFriends = friends.subList(0, Math.min(friends.size(), 6));


                profileAdapter = new ProfileAdapter(getApplicationContext(), firstSixFriends);
                recyclerViewFriendList.setAdapter(profileAdapter);

                countFriend.setText(String.valueOf(friends.size()));
            }

            @Override
            public void onFailure(String errorMessage) {

            }
        });
    }




}
