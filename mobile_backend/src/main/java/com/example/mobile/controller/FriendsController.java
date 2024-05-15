package com.example.mobile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mobile.dto.FriendRequestDTO;
import com.example.mobile.dto.ResponseDTO;
import com.example.mobile.mapper.UserMapper;
import com.example.mobile.model.User;
import com.example.mobile.service.UserService;

@RestController
@RequestMapping("/friend")
public class FriendsController {
	@Autowired
	UserService userService;

	@GetMapping("/accept")
	public ResponseEntity<?> getAcceptAddFriendList() {
		return ResponseEntity.ok().body(userService.findAllAcceptFriend().stream().map(User::getName));
	}

	@GetMapping("/suggest")
	public ResponseEntity<?> getSuggestAddFriendList() {
		try {

			return ResponseEntity
					.ok(userService.getSuggestAddFriend().stream().map(UserMapper.INSTANCE::userEntityToDTO).toList());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return ResponseEntity.badRequest().body(new ResponseDTO(e.getMessage()));
		}
	}

	@PostMapping("/addfriend")
	public ResponseEntity<?> addFriend(@RequestBody FriendRequestDTO dto) {
		try {
			userService.handleFriendRequest(dto);
			return ResponseEntity.ok(new ResponseDTO("Success"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return ResponseEntity.badRequest().body(new ResponseDTO(e.getMessage()));
		}
	}

	@PostMapping("/reject/addfriend")
	public ResponseEntity<?> rejectAddFriend(@RequestBody FriendRequestDTO dto) {
		try {
			userService.handleRejectFriendRequest(dto);
			return ResponseEntity.ok(new ResponseDTO("Success"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return ResponseEntity.badRequest().body(new ResponseDTO(e.getMessage()));
		}
	}

	@PostMapping("/accept/addfriend")
	public ResponseEntity<?> accpetAddFriend(@RequestBody FriendRequestDTO dto) {
		try {
			userService.handleAccpetFriendRequest(dto);
			return ResponseEntity.ok(new ResponseDTO("Success"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return ResponseEntity.badRequest().body(new ResponseDTO(e.getMessage()));
		}
	} 
	@PostMapping("/cancel/addfriend")
	public ResponseEntity<?> cancelAddFriend(@RequestBody FriendRequestDTO dto) {
		try {
			userService.handleCancelFriendRequest(dto);
			return ResponseEntity.ok(new ResponseDTO("Success"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return ResponseEntity.badRequest().body(new ResponseDTO(e.getMessage()));
		}
	}

	@GetMapping("/addfriend")
	public ResponseEntity<?> getAddFriends() {
		try {
			return ResponseEntity.ok(userService.getAllFriendRequest().stream()
					.map(UserMapper.INSTANCE::friendRequestEntityToDTO).toList());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return ResponseEntity.badRequest().body(new ResponseDTO(e.getMessage()));
		}
	}
}
