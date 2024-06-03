package com.example.myapplication.network.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
//@AllArgsConstructor
public class LoginRequest implements Serializable {
    private String phone;
    private String password;

    // Constructor chứa tất cả các thuộc tính
    public LoginRequest(String phone, String password) {
        this.phone = phone;
        this.password = password;
    }

}
