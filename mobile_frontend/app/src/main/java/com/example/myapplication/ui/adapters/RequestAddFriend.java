package com.example.myapplication.ui.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myapplication.R;
import com.example.myapplication.model.FriendRequestModel;
import de.hdodenhof.circleimageview.CircleImageView;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class RequestAddFriend extends RecyclerView.Adapter<RequestAddFriend.ViewHolder> {
    private Context mContext;
    private List<FriendRequestModel> mFriendRequests;

    public RequestAddFriend(Context context, List<FriendRequestModel> friendRequests) {
        mContext = context;
        mFriendRequests = friendRequests;
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
        holder.timeTextView.setText(friendRequest.getTime());

        // Sử lý sự kiện cho các nút
        holder.acceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.status.setVisibility(View.VISIBLE);
                holder.acceptButton.setVisibility(View.GONE);
                holder.deleteButton.setVisibility(View.GONE);
            }
        });
        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int clickedPosition = holder.getAdapterPosition();
                if (clickedPosition != RecyclerView.NO_POSITION) {
                    // Loại bỏ mục được click khỏi danh sách
                    mFriendRequests.remove(clickedPosition);
                    // Cập nhật RecyclerView
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
        public TextView timeTextView;
        public Button deleteButton;
        public Button acceptButton;
        public TextView status;

        public ViewHolder(View itemView) {
            super(itemView);
            status = itemView.findViewById(R.id.request_add_friend_status);
            avatarImageView = itemView.findViewById(R.id.request_add_friend_avatar);
            nameTextView = itemView.findViewById(R.id.request_add_friend_name);
            timeTextView = itemView.findViewById(R.id.request_add_friend_time_request);
            deleteButton = itemView.findViewById(R.id.request_add_friend_delete);
            acceptButton = itemView.findViewById(R.id.request_add_friend_accept);
        }
    }
}
