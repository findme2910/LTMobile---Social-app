package com.example.myapplication.model;

import java.text.SimpleDateFormat;
import java.util.Date;
// so sánh các thông báo dựa trên thời gian để tí nữa sort
public class Notification{

    private String content;
    private int avartar;
    private String name;
    private Date createdAt;
    private boolean active;

    public Notification(String content, int avartar, String name, Date createdAt, boolean active) {
        this.content = content;
        this.avartar = avartar;
        this.name = name;
        this.createdAt = createdAt;
        this.active = active;
    }

    public String getContent() {
        return content;
    }

    public int avartar() {
        return avartar;
    }

    public String getName() {
        return name;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public boolean isActive() {
        return active;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setAvartar(int avartar) {
        this.avartar = avartar;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
    public String getDateString (){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy 'lúc' HH:mm");
        String formattedDate = sdf.format(this.createdAt);
        return formattedDate;
    }
}
