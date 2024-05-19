package com.example.myapplication.ui.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
//còn 2 cái phải xử lý đó là sắp xếp theo thời gian, và check seen trong noti
public class NotificationActivity extends AppCompatActivity {
    private RecyclerView rcvNotiToday;
    private RecyclerView rcvNotiLater;
    private NotificationAdapter notiAdaptertoDay;
    private NotificationAdapter notiAdapterlater;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        notiAdaptertoDay = new NotificationAdapter(this);
        notiAdapterlater = new NotificationAdapter(this);
        //set adapter cho hôm nay
        LinearLayoutManager linearLayoutManagerToday = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        rcvNotiToday =findViewById(R.id.rv_items_today);
        rcvNotiToday.setLayoutManager(linearLayoutManagerToday);
        rcvNotiToday.setAdapter(notiAdaptertoDay);
        notiAdaptertoDay.setData(getListToday());
        //set adapter cho trước đây
        LinearLayoutManager linearLayoutManagerLater = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        rcvNotiLater = findViewById(R.id.rv_items_later);
        rcvNotiLater.setLayoutManager(linearLayoutManagerLater);
        rcvNotiLater.setAdapter(notiAdapterlater);
        notiAdapterlater.setData(getListLater());
        //Thêm ItemTouchHelper để xử lý vuốt
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false; // Không hỗ trợ kéo và thả
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                // Xử lý khi vuốt
                int position = viewHolder.getAdapterPosition();
                notiAdaptertoDay.removeItem(position);
            }
        });

        // Đính kèm ItemTouchHelper vào RecyclerView
        itemTouchHelper.attachToRecyclerView(rcvNotiToday);
        itemTouchHelper.attachToRecyclerView(rcvNotiLater);
    }
    //xử lý sau
    private List<Notification> getListLater(){
        List<Notification> list = new ArrayList<>();
        list.add(new Notification("Đã thích bài viết của bạn", R.drawable.avt_2, "Thái Sơn", new Date(2024 - 1900, 4, 2,6,30,30),false));
        list.add(new Notification("Đã chấp nhận lời mời kết bạn", R.drawable.avt_1, "Quang Huy", new Date(2024 - 1900, 4, 2,7,30,30),false));
        list.add(new Notification("Đã thích bài viết của bạn", R.drawable.avt_3, "Hoài Thương", new Date(2022 - 1900, 12, 14,6,30,30),true));
        list.add(new Notification("Đã thích bài viết của bạn", R.drawable.avt_4, "Hữu Nhân", new Date(2020 - 1900, 12, 14,6,30,30),true));
        list.add(new Notification("Đã thích bài viết của bạn", R.drawable.avt_5, "Hoài Nam", new Date(2020 - 1900, 12, 14,6,30,30),true));
        return list;
    }
    private List<Notification> getListToday(){
        List<Notification> list = new ArrayList<>();
        list.add(new Notification("Đã thích bài viết của bạn", R.drawable.avt_2, "Thái Sơn", new Date(2024 - 1900, 4, 2,6,30,30),false));
        list.add(new Notification("Đã chấp nhận lời mời kết bạn", R.drawable.avt_1, "Quang Huy", new Date(2024 - 1900, 4, 2,7,30,30),false));
        list.add(new Notification("Đã thích bài viết của bạn", R.drawable.avt_3, "Hoài Thương", new Date(2022 - 1900, 12, 14,6,30,30),true));
        list.add(new Notification("Đã thích bài viết của bạn", R.drawable.avt_4, "Hữu Nhân", new Date(2020 - 1900, 12, 14,6,30,30),true));
        list.add(new Notification("Đã thích bài viết của bạn", R.drawable.avt_5, "Hoài Nam", new Date(2020 - 1900, 12, 14,6,30,30),true));
        return list;
    }
    //xử lý logic nó nên là recyclerview hôm nay hay trước đây
}
