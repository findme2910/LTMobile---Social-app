package com.example.mobile.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mobile.dto.AddPostDTO;
import com.example.mobile.dto.LikeDTO;
import com.example.mobile.dto.ResponseDTO;
import com.example.mobile.mapper.PostMapper;
import com.example.mobile.model.Post;
import com.example.mobile.service.PostService;
import com.example.mobile.service.UserService;

@RestController
@RequestMapping("/post")
public class PostController {
	@Autowired
	PostService postService;
	@Autowired
	UserService userService;

	@PostMapping
	public ResponseEntity<?> create(@RequestBody AddPostDTO dto) {
		try {
			postService.save(dto);
			return ResponseEntity.ok(new ResponseDTO("Success"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return ResponseEntity.badRequest().body(new ResponseDTO("Failure"));
		}

	}

	@GetMapping
	public ResponseEntity<?> get() {
		try {
			List<Post> posts = postService.get();
			return ResponseEntity.ok(posts.stream().map(PostMapper.INSTANCE::entityToViewPostDTO));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(new ResponseDTO("Failure"));
		}

	}

	@PostMapping("/like")
	public ResponseEntity<?> like(@RequestBody LikeDTO dto) {
		try {
			userService.like(dto);
			return ResponseEntity.ok(new ResponseDTO("Success"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return ResponseEntity.badRequest().body(new ResponseDTO("Failure"));
		}
	}
}
