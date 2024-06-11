package com.example.myapplication.network.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDTO {
    private String name;
    private String phone;
    private String gender;
    private long birth;
    private String password;
}
