package com.example.myapplication.ui.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.convert.DateConvert;
import com.example.myapplication.convert.ImageConvert;
import com.example.myapplication.model.Notification;
import com.example.myapplication.ui.fragment.CommentsFragment;

import java.util.List;

// trong đây sẽ khai báo logic sắp xếp các item trong thông báo
public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder>{
    private List<Notification> listNoti;
    private Context ctx;

    public NotificationAdapter(Context ctx) {
        this.ctx = ctx;
    }

    public NotificationAdapter(List<Notification> listNoti, Context ctx) {
        this.listNoti = listNoti;
        this.ctx = ctx;
    }

    public void setData(List<Notification> list){
        this.listNoti = list;
        notifyDataSetChanged();
    }

    public void removeItem(int position) {
        listNoti.remove(position);
        notifyItemRemoved(position);
    }
    @NonNull
    @Override
    public NotificationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notification,parent,false);
        return new NotificationAdapter.ViewHolder(view);
    }

    // Chỗ này có thể sử lý logic người ta đã đọc hay chưa
    @Override
    public void onBindViewHolder(@NonNull NotificationAdapter.ViewHolder holder, int position) {
        Notification noti = listNoti.get(position);
        if (noti == null) return;
        ImageView imageView = holder.imgUser;
        imageView.setImageBitmap(ImageConvert.base64ToBitMap(noti.getAvatar()));
        holder.tvContent.setText(noti.getContent());
        holder.tvName.setText(noti.getName());
        holder.tvDate.setText(DateConvert.convertToString(noti.getCreatedAt().getTime()));
        if(!noti.isActive()) holder.relativeLayout.setBackgroundResource(R.drawable.background_drawable);
        holder.relativeLayout.setOnClickListener(view -> {
            Context context = view.getContext();
            if (context instanceof FragmentActivity) {
                FragmentActivity activity = (FragmentActivity) context;
                CommentsFragment commentsFragment = new CommentsFragment();
                Bundle args = new Bundle();
                args.putInt("postID", noti.getPostId());
                commentsFragment.setArguments(args);
                activity.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frameLayout, commentsFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listNoti != null ? listNoti.size() : 0;
    }



    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView imgUser;
        private TextView tvName;
        private TextView tvDate;
        private TextView tvContent;
        private RelativeLayout relativeLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgUser = itemView.findViewById(R.id.img_user);
            tvName = itemView.findViewById(R.id.tv_name);
            tvContent = itemView.findViewById(R.id.tv_content);
            tvDate = itemView.findViewById(R.id.date_label);
            relativeLayout = itemView.findViewById(R.id.container);
        }


    }
}
