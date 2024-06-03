package com.example.myapplication.ui.activities;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myapplication.R;
import com.example.myapplication.convert.DateConvert;
import com.example.myapplication.model.FriendRequestModel;
import com.example.myapplication.network.api.Friend.FriendAddManager;
import com.example.myapplication.network.api.HandleListener;
import com.example.myapplication.network.api.HelloManager;
import com.example.myapplication.network.model.dto.IfReqAddFiendDTO;
import com.example.myapplication.ui.adapters.AddFriendAdapter;
import com.example.myapplication.ui.adapters.RequestAddFriend;

import java.util.ArrayList;
import java.util.List;

public class AddFriendActivity extends AppCompatActivity {
//    private RecyclerView addFriendRecyclerView, requestAddFriendRecyclerView;
//    private AddFriendAdapter addFriendAdapter;
//    private RequestAddFriend requestAddFriendAdapter;
//    private List<FriendRequestModel> mFriendRequests, requestAddFriend;
//
//    private TextView numberOfRequest;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_add_friend);
//
//
//        numberOfRequest = findViewById(R.id.numberOfRequest);
//
//        mFriendRequests = new ArrayList<>();
//
//
//        // recycle view add friend
//        addFriendRecyclerView = findViewById(R.id.list_add_friend);
//        addFriendRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//
//
//        // recycle view request add friend
//        requestAddFriendRecyclerView = findViewById(R.id.list_request_add_friend);
//        requestAddFriendRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//
//        requestAddFriend = new ArrayList<>();
//    }
//
//    @Override
//    protected void onStart() {
//        super.onStart();
//        FriendAddManager friendAddManager = new FriendAddManager();
//        friendAddManager.getFriendAdds(new HandleListener<List<IfReqAddFiendDTO>>() {
//            @Override
//            public void onSuccess(List<IfReqAddFiendDTO> ifReqAddFiendDTOs) {
////                ((TextView)findViewById(R.id.numberOfRequest)).setText(ifReqAddFiendDTOs.size());
//                for (IfReqAddFiendDTO x : ifReqAddFiendDTOs) {
//
//                    mFriendRequests.add(new FriendRequestModel(x.getUserId(), x.getName(), DateConvert.convertToString(x.getTime()), x.getAvatar()));
//                }
//                // Khởi tạo dữ liệu
//
//                addFriendAdapter = new AddFriendAdapter(getApplicationContext(), mFriendRequests);
//                addFriendRecyclerView.setAdapter(addFriendAdapter);
//            }
//
//            @Override
//            public void onFailure(String errorMessage) {
//
//            }
//        });
//        friendAddManager.getSuggestFriendAdds(new HandleListener<List<IfReqAddFiendDTO>>() {
//            @Override
//            public void onSuccess(List<IfReqAddFiendDTO> ifReqAddFiendDTOs) {
//                for (IfReqAddFiendDTO x : ifReqAddFiendDTOs) {
//                    requestAddFriend.add(new FriendRequestModel(x.getUserId(), x.getName(), null, x.getAvatar()));
//                }
//                // Khởi tạo dữ liệu
//
//                requestAddFriendAdapter = new RequestAddFriend(getApplicationContext(), requestAddFriend);
//                requestAddFriendRecyclerView.setAdapter(requestAddFriendAdapter);
//            }
//
//            @Override
//            public void onFailure(String errorMessage) {
//
//            }
//        });
//    }
}