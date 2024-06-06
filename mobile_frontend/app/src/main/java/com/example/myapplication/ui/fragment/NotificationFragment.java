package com.example.myapplication.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.Notification;
import com.example.myapplication.network.api.HandleListener;
import com.example.myapplication.network.api.Notification.NotificationManager;
import com.example.myapplication.network.model.dto.NotificationDTO;
import com.example.myapplication.network.model.dto.RequestNotificationDTO;
import com.example.myapplication.ui.adapters.NotificationAdapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class NotificationFragment extends Fragment {

    private RecyclerView rcvNotiToday;
    private RecyclerView rcvNotiLater;
    private NotificationAdapter notiAdaptertoDay;
    private NotificationAdapter notiAdapterlater;
    private int currentPage = 0;
    private boolean isLoading = false;

    public NotificationFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notification, container, false);

        notiAdaptertoDay = new NotificationAdapter(getContext());
        notiAdapterlater = new NotificationAdapter(getContext());

        // Set adapter cho hôm nay
        LinearLayoutManager linearLayoutManagerToday = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        rcvNotiToday = view.findViewById(R.id.rv_items_today);
        rcvNotiToday.setLayoutManager(linearLayoutManagerToday);
        rcvNotiToday.setAdapter(notiAdaptertoDay);

        // Set adapter cho trước đây
        LinearLayoutManager linearLayoutManagerLater = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        rcvNotiLater = view.findViewById(R.id.rv_items_later);
        rcvNotiLater.setLayoutManager(linearLayoutManagerLater);
        rcvNotiLater.setAdapter(notiAdapterlater);

        // Thêm ItemTouchHelper để xử lý vuốt
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

        // Thêm trình nghe sự kiện cuộn vào NestedScrollView
        NestedScrollView nestedScrollView = view.findViewById(R.id.nested_scroll_view);
        nestedScrollView.setOnScrollChangeListener((NestedScrollView.OnScrollChangeListener) (v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
            if (scrollY == (v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight())) {
                // Đã cuộn đến cuối danh sách
                if (!isLoading) {
                    isLoading = true;
                    currentPage++;
                    requestNotification(currentPage);
                }
            }
        });

        requestNotification(currentPage);
        return view;
    }

    private void requestNotification(int next) {
        NotificationManager notificationManager = new NotificationManager();
        RequestNotificationDTO dto = new RequestNotificationDTO(next);
        notificationManager.requestNotification(dto, new HandleListener<List<NotificationDTO>>() {
            @Override
            public void onSuccess(List<NotificationDTO> notificationDTOS) {
                List<Notification> notificationsToday = new ArrayList<>();
                List<Notification> notificationsLater = new ArrayList<>();

                for (NotificationDTO dto : notificationDTOS) {
                    Notification notification = new Notification(dto.getContent(), dto.getAvatar(), dto.getName(), new Date(dto.getCreateAt()), true);
                    if (isToday(new Date(dto.getCreateAt()))) {
                        notificationsToday.add(notification);
                    } else {
                        notificationsLater.add(notification);
                    }
                }
                // Sắp xếp thông báo theo ngày
                sortNotificationsByDate(notificationsToday);
                sortNotificationsByDate(notificationsLater);
                // Cập nhật dữ liệu cho các adapter
                notiAdaptertoDay.setData(notificationsToday);
                notiAdapterlater.setData(notificationsLater);
                isLoading = false;
            }

            @Override
            public void onFailure(String errorMessage) {
                Toast.makeText(getContext(), "Failed to load notifications: " + errorMessage, Toast.LENGTH_SHORT).show();
                isLoading = false;
            }
        });
    }

    private boolean isToday(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        Calendar today = Calendar.getInstance();
        return calendar.get(Calendar.YEAR) == today.get(Calendar.YEAR) &&
                calendar.get(Calendar.DAY_OF_YEAR) == today.get(Calendar.DAY_OF_YEAR);
    }

    private void sortNotificationsByDate(List<Notification> notifications) {
        Collections.sort(notifications, new Comparator<Notification>() {
            @Override
            public int compare(Notification n1, Notification n2) {
                return n2.getCreatedAt().compareTo(n1.getCreatedAt());
            }
        });
    }
}
