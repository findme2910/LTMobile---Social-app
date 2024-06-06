package com.example.myapplication.ui.fragment;

import android.os.Bundle;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.example.myapplication.R;
import com.example.myapplication.convert.DateConvert;
import com.example.myapplication.model.FriendRequestModel;
import com.example.myapplication.network.api.Friend.FriendAddManager;
import com.example.myapplication.network.api.HandleListener;
import com.example.myapplication.network.model.dto.IfReqAddFiendDTO;
import com.example.myapplication.ui.adapters.AddFriendAdapter;
import com.example.myapplication.ui.adapters.RequestAddFriend;

import java.util.ArrayList;
import java.util.List;

public class AddFriendFragment extends Fragment {
    private RecyclerView addFriendRecyclerView, requestAddFriendRecyclerView;
    private AddFriendAdapter addFriendAdapter;
    private RequestAddFriend requestAddFriendAdapter;
    private List<FriendRequestModel> mFriendRequests, requestAddFriend;
    private SwipeRefreshLayout swipeRefreshLayout;

    private TextView numberOfRequest;

    public AddFriendFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_add_friend, container, false);

        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Đây là nơi bạn thực hiện các hoạt động cần thiết để làm mới dữ liệu
                // Sau khi hoàn thành, bạn cần gọi phương thức setRefreshing(false) để dừng hiệu ứng làm mới
                // Ví dụ: load dữ liệu mới từ mạng hoặc cập nhật dữ liệu từ cơ sở dữ liệu
                loadData(view);
            }
        });
        loadData(view);
        mFriendRequests = new ArrayList<>();

        // recycle view add friend
        addFriendRecyclerView = view.findViewById(R.id.list_add_friend);
        addFriendRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        numberOfRequest = view.findViewById(R.id.numberOfRequest);

        // recycle view request add friend
        requestAddFriendRecyclerView = view.findViewById(R.id.list_request_add_friend);
        requestAddFriendRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        requestAddFriend = new ArrayList<>();
        return view;
    }
    private void loadData(View view) {
        FriendAddManager friendAddManager = new FriendAddManager();
        friendAddManager.getFriendAdds(new HandleListener<List<IfReqAddFiendDTO>>() {
            @Override
            public void onSuccess(List<IfReqAddFiendDTO> ifReqAddFiendDTOs) {
                mFriendRequests.clear();
                numberOfRequest.setText(ifReqAddFiendDTOs.size()+"");
                for (IfReqAddFiendDTO x : ifReqAddFiendDTOs) {

                    mFriendRequests.add(new FriendRequestModel(x.getUserId(), x.getName(), DateConvert.convertToString(x.getTime()), x.getAvatar()));
                }
                // Khởi tạo dữ liệu

                addFriendAdapter = new AddFriendAdapter(view.getContext(), mFriendRequests);
                addFriendRecyclerView.setAdapter(addFriendAdapter);
            }

            @Override
            public void onFailure(String errorMessage) {

            }
        });
        friendAddManager.getSuggestFriendAdds(new HandleListener<List<IfReqAddFiendDTO>>() {
            @Override
            public void onSuccess(List<IfReqAddFiendDTO> ifReqAddFiendDTOs) {
                requestAddFriend.clear();
                for (IfReqAddFiendDTO x : ifReqAddFiendDTOs) {
                    requestAddFriend.add(new FriendRequestModel(x.getUserId(), x.getName(), null, x.getAvatar()));
                }
                // Khởi tạo dữ liệu

                requestAddFriendAdapter = new RequestAddFriend(view.getContext(), requestAddFriend);
                requestAddFriendRecyclerView.setAdapter(requestAddFriendAdapter);
            }

            @Override
            public void onFailure(String errorMessage) {

            }
        });
        swipeRefreshLayout.setRefreshing(false);
    }
}