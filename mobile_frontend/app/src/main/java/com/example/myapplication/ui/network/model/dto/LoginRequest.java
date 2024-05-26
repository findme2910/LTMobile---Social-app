package com.example.myapplication.ui.network.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class LoginRequest implements Serializable {
    private String phone;
    private String password;
}