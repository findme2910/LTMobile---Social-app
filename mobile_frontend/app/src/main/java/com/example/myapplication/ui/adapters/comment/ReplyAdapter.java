package com.example.myapplication.ui.adapters.comment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.convert.DateConvert;
import com.example.myapplication.convert.ImageConvert;
import com.example.myapplication.model.Comment.Comment;

import java.util.List;

public class ReplyAdapter extends RecyclerView.Adapter<ReplyAdapter.ReplyViewHolder> {

    private Context context;
    private List<Comment> replyList;

    public ReplyAdapter(Context context, List<Comment> replyList) {
        this.context = context;
        this.replyList = replyList;
    }

    @NonNull
    @Override
    public ReplyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_reply, parent, false);
        return new ReplyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReplyViewHolder holder, int position) {
        Comment reply = replyList.get(position);
        holder.textViewUsername.setText(reply.getUserName());
        holder.textViewComment.setText(reply.getText());
        holder.textViewCreateAt.setText(DateConvert.convertToString(reply.getCreateAt().getTime()));
        ImageView imageView = holder.imageViewAvatar;
        imageView.setImageBitmap(ImageConvert.base64ToBitMap(reply.getAvatar()));
    }

    @Override
    public int getItemCount() {
        return replyList.size();
    }

    public static class ReplyViewHolder extends RecyclerView.ViewHolder {
        TextView textViewUsername, textViewComment, textViewCreateAt;
        ImageView imageViewAvatar;;

        public ReplyViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewUsername = itemView.findViewById(R.id.replyTextViewUsername);
            textViewComment = itemView.findViewById(R.id.replyTextViewComment);
            textViewCreateAt = itemView.findViewById(R.id.replyTextViewCreateAt);
            imageViewAvatar = itemView.findViewById(R.id.replyImageViewAvatar);
        }
    }
}