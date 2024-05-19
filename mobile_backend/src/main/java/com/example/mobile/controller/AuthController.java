package com.example.mobile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mobile.dto.LoginDTO;
import com.example.mobile.dto.RegisterDTO;
import com.example.mobile.dto.ResponseDTO;
import com.example.mobile.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	UserService userService;

	@PostMapping("/login")
	public ResponseEntity<ResponseDTO> login(@RequestBody LoginDTO loginDTO) {
		try {
			return ResponseEntity.ok(new ResponseDTO(userService.login(loginDTO)));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new ResponseDTO(e.getMessage()));
		}

	}

	@PostMapping("/register")
	public ResponseEntity<?> add(@RequestBody RegisterDTO dto) {
		try {
			userService.register(dto);
			return ResponseEntity.ok(new ResponseDTO("Success"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return ResponseEntity.badRequest().body(new ResponseDTO(e.getMessage()));
		}
	}
//	@PostMapping("/")
//	public ResponseEntity<?> add(@RequestBody RegisterDTO dto) {
//		try {
//			userService.register(dto);
//			return ResponseEntity.ok(new ResponseDTO("Success"));
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			return ResponseEntity.badRequest().body(new ResponseDTO("Failure"));
//		}
//	}
}
