package com.example.mobile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mobile.dto.AddPostDTO;
import com.example.mobile.dto.ResponseDTO;
import com.example.mobile.service.PostService;

@RestController
@RequestMapping("/post")
public class PostController {
	@Autowired
	PostService postService;

	@PostMapping
	public ResponseEntity<?> add(@RequestBody AddPostDTO dto) {
		try {
			postService.save(dto);
			return ResponseEntity.ok(new ResponseDTO("Success"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return ResponseEntity.badRequest().body(new ResponseDTO("Failure"));
		}

	}
}
