package com.example.myapplication.ui.activities;

import java.text.SimpleDateFormat;
import java.util.Date;
// so sánh các thông báo dựa trên thời gian để tí nữa sort
public class Notification implements  Comparable<Notification>{
    private String content;
    private int resourceId;
    private String name;
    private Date createdAt;
    private boolean active;

    public Notification(String content, int resourceId, String name, Date createdAt, boolean active) {
        this.content = content;
        this.resourceId = resourceId;
        this.name = name;
        this.createdAt = createdAt;
        this.active = active;
    }

    public String getContent() {
        return content;
    }

    public int getResourceId() {
        return resourceId;
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

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
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
    @Override
    public int compareTo(Notification other) {
        return getCreatedAt().compareTo(other.getCreatedAt());
    }
}
