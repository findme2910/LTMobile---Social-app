package com.example.myapplication.ui.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.convert.DateConvert;
import com.example.myapplication.convert.DateConvertProfile;
import com.example.myapplication.convert.ImageConvert;
import com.example.myapplication.convert.ImageConvertProfile;
import com.example.myapplication.model.Post;
import com.example.myapplication.network.api.HandleListener;
import com.example.myapplication.network.api.Profile.ProfileManager;
import com.example.myapplication.network.model.dto.FriendViewDTO;
import com.example.myapplication.network.model.dto.ProfileDTO;
import com.example.myapplication.network.model.instance.JwtTokenManager;
import com.example.myapplication.ui.adapters.PostAdapter;
import com.example.myapplication.ui.adapters.ProfileAdapter;

import java.util.ArrayList;
import java.util.List;

public class ProfileActivity extends AppCompatActivity {
    private ActivityResultLauncher<Intent> profileEditLauncher;
    private static final int YOUR_REQUEST_CODE = 20;
    private int selectedUserId; // Biến để lưu ID người dùng đã chọn
    private int loggedUser; // Biến để lưu ID người dùng đang đăng nhập
    private RecyclerView recyclerViewFriendList, postsRecyclerView;
    private ProfileAdapter profileAdapter;
    private PostAdapter postAdapter;

    private List<Post> posts;

    private List<FriendViewDTO> friends;

    private TextView countFriend;
    private TextView profileName, profileBirthday;

    private ImageView userAvatar;
    private Button editProfileButton, friendsListButton, isFriendButtton;


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


        editProfileButton = findViewById((R.id.edit_profile_button));
        // Khai báo biến cho ActivityResultLauncher
        profileEditLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        // Xử lý kết quả ở đây
                        Intent data = result.getData();
                        if (data != null) {
                            String newAvatar = data.getStringExtra("NEW_AVATAR");
                            if (newAvatar != null && !newAvatar.isEmpty()) {
                                userAvatar.setImageBitmap(ImageConvertProfile.base64ToBitMap(newAvatar));
                            }
                        }
                    }
                }
        );

    // Trong onClickListener của nút chỉnh sửa
        editProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ProfileActivity.this, ProfileEditActivity.class);
                profileEditLauncher.launch(i); // Bắt đầu ProfileEditActivity
            }
        });


        friendsListButton = findViewById((R.id.all_friends_button));

        friendsListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ProfileActivity.this, FriendsListActivity.class);
                i.putExtra("USER_ID", selectedUserId); // Truyền ID người dùng đã chọn vào Intent
                startActivity(i);
            }
        });

        loadUserProfile();

    }


    private void loadUserProfile() {
        super.onStart();
        ProfileManager profileManager = new ProfileManager();
        profileManager.getProfile(new HandleListener<ProfileDTO>() {
            @Override
            public void onSuccess(ProfileDTO profileDTO) {
                profileName.setText(profileDTO.getName());
                profileBirthday.setText(DateConvertProfile.convertToString(profileDTO.getBirth()));
                userAvatar.setImageBitmap(ImageConvertProfile.base64ToBitMap(profileDTO.getAvatar()));

                friends = profileDTO.getFriends();
                selectedUserId = profileDTO.getUserId();
                loggedUser =  profileDTO.getUserId();

//                Lấy 6 bạn đầu tiên
                List<FriendViewDTO> firstSixFriends = friends.subList(0, Math.min(friends.size(), 6));


                profileAdapter = new ProfileAdapter(getApplicationContext(), firstSixFriends);
                recyclerViewFriendList.setAdapter(profileAdapter);

                countFriend.setText(String.valueOf(friends.size()));

                // Thiết lập ProfileClickListener cho profileAdapter
                profileAdapter.setProfileClickListener(new ProfileAdapter.ProfileClickListener() {
                    @Override
                    public void onProfileClicked(int userId) {
                        selectedUserId = userId;
                        System.out.println("selectedUser: " + selectedUserId);

                        System.out.println("loggedUser: " + loggedUser);

                        System.out.println("userId: " + userId);


                        // Kiểm tra nếu user được click là bạn bè
                        if (userId == loggedUser) {
                            editProfileButton.setText("Chỉnh sửa trang cá nhân");
                            editProfileButton.setEnabled(true);
                        } else if (isFriend(userId)) {
                            editProfileButton.setText("Đã là bạn bè");
                            editProfileButton.setEnabled(false);
                        } else {

                            editProfileButton.setText("Kết bạn");
                            editProfileButton.setEnabled(false);
                        }
                        // Gọi API để lấy thông tin profile của user được chọn
                        profileManager.getProfileForUser(userId, new HandleListener<ProfileDTO>() {
                            @Override
                            public void onSuccess(ProfileDTO profileDTO) {
                                profileName.setText(profileDTO.getName());
                                profileBirthday.setText(DateConvertProfile.convertToString(profileDTO.getBirth()));
                                userAvatar.setImageBitmap(ImageConvertProfile.base64ToBitMap(profileDTO.getAvatar()));


                                friends = profileDTO.getFriends();

                                //                Lấy 6 bạn đầu tiên
                                List<FriendViewDTO> firstSixFriends = friends.subList(0, Math.min(friends.size(), 6));


                                profileAdapter = new ProfileAdapter(getApplicationContext(), firstSixFriends);
                                recyclerViewFriendList.setAdapter(profileAdapter);

                                countFriend.setText(String.valueOf(friends.size()));



                            }

                            @Override
                            public void onFailure(String errorMessage) {
                                // Xử lý khi có lỗi xảy ra
                            }
                        });
                    }
                });
            }

            @Override
            public void onFailure(String errorMessage) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == YOUR_REQUEST_CODE && resultCode == RESULT_OK) {
            // Nhận hình ảnh mới từ Intent
            String newAvatar = data.getStringExtra("NEW_AVATAR");

            // Cập nhật hình ảnh trong giao diện
            if (newAvatar != null && !newAvatar.isEmpty()) {
                userAvatar.setImageBitmap(ImageConvertProfile.base64ToBitMap(newAvatar));
            }
        }
    }

    // Phương thức kiểm tra nếu user được click là bạn bè
    private boolean isFriend(int userId) {
        for (FriendViewDTO friend : friends) {
            if (friend.getUserId() == userId) {
                return true;
            }
        }
        return false;
    }



}