package com.example.myapplication.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.Friend;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import lombok.Getter;
import lombok.Setter;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ViewHolder> {
    private Context mContext;
    private List<Friend> friends;

    public ProfileAdapter(Context context, List<Friend> friendList) {
        mContext = context;
        friends = friendList;
    }

    @NonNull
    @NotNull
    @Override
    public ProfileAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.profile_friend_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileAdapter.ViewHolder holder, int position) {
        Friend friend = friends.get(position);
        holder.nameTextView.setText(friend.getName());

        // Hiển thị hình ảnh trong ImageView
//        ImageView imageView = holder.avatarImageView;
//        imageView.setImageBitmap(ImageConvert.base64ToBitMap(friend.getAvatar()));
    }

    @Override
    public int getItemCount() {
        return friends.size();
    }

    @Setter
    @Getter
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView avatarImageView;
        public TextView nameTextView;
        public ViewHolder(View itemView) {
            super(itemView);
            avatarImageView = itemView.findViewById(R.id.profile_friend_item_avatar);
            nameTextView = itemView.findViewById(R.id.profile_friend_item_name);
        }
    }



}
