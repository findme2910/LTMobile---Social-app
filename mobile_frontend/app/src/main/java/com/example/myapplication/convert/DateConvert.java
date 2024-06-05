package com.example.myapplication.convert;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateConvert {
    public static String formatTime(long timeInMillis) {
        // Lấy thời gian hiện tại
        long currentTime = System.currentTimeMillis();

        // Tính toán khoảng thời gian giữa thời gian hiện tại và thời gian tạo bài viết
        long timeDiff = currentTime - timeInMillis;

        // Nếu thời gian tạo bài viết là trong vòng 1 phút, hiển thị "Vừa xong"
        if (timeDiff < 60000) {
            return "Vừa xong";
        }

        // Nếu thời gian tạo bài viết là trong vòng 1 giờ, hiển thị số phút trước
        if (timeDiff < 3600000) {
            long minutesAgo = timeDiff / 60000;
            return minutesAgo + " phút trước";
        }

        // Nếu thời gian tạo bài viết là trong vòng 1 ngày, hiển thị số giờ trước
        if (timeDiff < 86400000) {
            long hoursAgo = timeDiff / 3600000;
            return hoursAgo + " giờ trước";
        }

        // Nếu thời gian tạo bài viết là trong vòng 1 tuần, hiển thị số ngày trước
        if (timeDiff < 604800000) {
            long daysAgo = timeDiff / 86400000;
            return daysAgo + " ngày trước";
        }

        // Nếu thời gian tạo bài viết là sau 1 tuần, hiển thị ngày tháng năm
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        return dateFormat.format(new Date(timeInMillis));
    }
    public static String convertToString(long date) {
        long diff = new Date().getTime() - date;
        long seconds = diff / 1000;
        long minutes = seconds / 60;
        long hours = minutes / 60;
        long days = hours / 24;
        long weeks = days / 7;
        long months = days / 30; // Giả sử mỗi tháng có khoảng 30 ngày
        // Xử lý dữ liệu để tạo chuỗi đại diện cho thời gian đã trôi qua
        if (months > 0) {
            return months + " tháng";
        } else if (weeks > 0) {
            return weeks + " tuần";
        } else if (days > 0) {
            return days + " ngày";
        } else if (hours > 0) {
            return hours + " giờ";
        } else if (minutes > 0) {
            return minutes + " phút";
        } else {
            return seconds + " giây";
        }
    }
}
