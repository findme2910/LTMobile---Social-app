package com.example.mobile.dto;

import lombok.Data;

@Data
public class AddUserDTO {
	private String name;
	private String phone;
	private long birth;
	private String password;
}
