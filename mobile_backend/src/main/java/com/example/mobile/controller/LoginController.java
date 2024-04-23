package com.example.mobile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.mobile.config.security.JwtUtils;
import com.example.mobile.dto.LoginDTO;
import com.example.mobile.dto.ResponseDTO;


@RestController
@RequestMapping("/login")
public class LoginController {
	@Autowired
	JwtUtils jwtUtils;
	@PostMapping
	public ResponseEntity<ResponseDTO> login(@RequestBody LoginDTO loginDTO) {
		
		return ResponseEntity.ok(new ResponseDTO(jwtUtils.gennerateJwtToken(loginDTO.username)));
	}
}
