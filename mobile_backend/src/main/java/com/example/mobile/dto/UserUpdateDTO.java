package com.example.mobile.dto;

import lombok.Data;

@Data
public class UserUpdateDTO {
	private String name;
	private String avatar;
	private String password;
	private long birth;
}
