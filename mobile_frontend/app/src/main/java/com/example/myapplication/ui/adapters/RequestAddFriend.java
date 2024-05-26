package com.example.myapplication.ui.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myapplication.R;
import com.example.myapplication.model.FriendRequestModel;
import com.example.myapplication.ui.network.api.Friend.FriendAddManager;
import com.example.myapplication.ui.network.api.HandleListener;
import de.hdodenhof.circleimageview.CircleImageView;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class RequestAddFriend extends RecyclerView.Adapter<RequestAddFriend.ViewHolder> {
    private Context mContext;
    private List<FriendRequestModel> mFriendRequests;
    private FriendAddManager friendAddManager;

    public RequestAddFriend(Context context, List<FriendRequestModel> friendRequests) {
        mContext = context;
        mFriendRequests = friendRequests;
        friendAddManager = new FriendAddManager();
    }

    @NonNull
    @NotNull
    @Override
    public RequestAddFriend.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.request_add_friend_item, parent, false);
        RequestAddFriend.ViewHolder viewHolder = new RequestAddFriend.ViewHolder(listItem);
        return viewHolder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull @NotNull RequestAddFriend.ViewHolder holder, int position) {
        FriendRequestModel friendRequest = mFriendRequests.get(position);
        holder.nameTextView.setText(friendRequest.getName());
        byte[] decodedBytes = Base64.decode(friendRequest.getAvatar(), Base64.DEFAULT);

        // Chuyển mảng byte thành hình ảnh Bitmap
        Bitmap bitmap = BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);

        // Hiển thị hình ảnh trong ImageView
        ImageView imageView = holder.avatarImageView;
        imageView.setImageBitmap(bitmap);




        // Sử lý sự kiện cho các nút
        holder.addFriendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                friendAddManager.requestAddFriend(friendRequest.getUserId(), new HandleListener<String>() {
                    @Override
                    public void onSuccess(String s) {
                        holder.status.setVisibility(View.VISIBLE);
                        holder.addFriendButton.setVisibility(View.GONE);
                        holder.deleteButton.setVisibility(View.GONE);
                    }
                    @Override
                    public void onFailure(String errorMessage) {
                    }
                });
            }
        });
        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int clickedPosition = holder.getAdapterPosition();
                if (clickedPosition != RecyclerView.NO_POSITION) {
                    // Loại bỏ mục được click khỏi danh sách
                    mFriendRequests.remove(clickedPosition);
                    notifyItemRemoved(clickedPosition);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mFriendRequests.size();
    }

    @Setter
    @Getter
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public CircleImageView avatarImageView;
        public TextView nameTextView;
        public Button deleteButton;
        public Button addFriendButton;
        public TextView status;

        public ViewHolder(View itemView) {
            super(itemView);
            status = itemView.findViewById(R.id.request_add_friend_status);
            avatarImageView = itemView.findViewById(R.id.request_add_friend_avatar);
            nameTextView = itemView.findViewById(R.id.request_add_friend_name);
            deleteButton = itemView.findViewById(R.id.request_add_friend_delete);
            addFriendButton = itemView.findViewById(R.id.request_add_friend_accept);
        }
    }
}
