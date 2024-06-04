package com.example.myapplication.convert;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConvert {
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
