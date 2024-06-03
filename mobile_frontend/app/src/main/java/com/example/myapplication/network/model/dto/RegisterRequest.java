package com.example.myapplication.network.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class RegisterRequest implements Serializable {
    private String name;
    private String phone;
    private String gender;
    private long birth;
    private String password;

}
