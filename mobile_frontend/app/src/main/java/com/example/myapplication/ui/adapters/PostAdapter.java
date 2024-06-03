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
import com.example.myapplication.model.Post;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {
    private Context mContext;
    private List<Post> posts;

    public PostAdapter(Context context, List<Post> postList) {
        mContext = context;
        posts = postList;
    }

    @NonNull
    @Override
    public PostAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.profile_post_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PostAdapter.ViewHolder holder, int position) {
        Post post = posts.get(position);
        holder.nameTextView.setText(post.getUsername());
        holder.postContentTextView.setText(post.getContent());

        // Thiết lập hình ảnh hoặc các thành phần khác nếu có

        holder.likeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.isLiked) {
                    holder.likeButton.setImageResource(R.drawable.icon_heart); // Đổi lại hình ảnh không thích
                    holder.isLiked = false;
                } else {
                    holder.likeButton.setImageResource(R.drawable.icon_heart_pink); // Đổi lại hình ảnh thích
                    holder.isLiked = true;
                }
            }
        });

        holder.commentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.isCommented) {
                    holder.commentButton.setImageResource(R.drawable.icon_comment_blue);
                    holder.isCommented = false;
                } else {
                    holder.commentButton.setImageResource(R.drawable.ic_comment);
                    holder.isCommented = true;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView profileImageView;
        public TextView nameTextView, postContentTextView;
        public ImageButton likeButton, commentButton;
        public boolean isLiked = false;
        private boolean isCommented = false;
        public ViewHolder(View itemView) {
            super(itemView);
            profileImageView = itemView.findViewById(R.id.profile_post_image);
            nameTextView = itemView.findViewById(R.id.post_user_name);
            postContentTextView = itemView.findViewById(R.id.post_content);
            likeButton = itemView.findViewById(R.id.like_button);
            commentButton = itemView.findViewById(R.id.comment_button);
        }
    }
}
