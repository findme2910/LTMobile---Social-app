package com.example.mobile.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mobile.dto.AddPostDTO;
import com.example.mobile.model.Post;
import com.example.mobile.model.User;
import com.example.mobile.repository.PostRepository;
import com.example.mobile.repository.UserRepository;

@Service
public class PostServiceImp implements PostService {
	@Autowired
	PostRepository postRepository;

	@Autowired
	UserRepository userRepository;

	@Override
	public void save(AddPostDTO dto) throws Exception {
		User user = userRepository.findById(dto.getUserId()).orElseThrow();

		Post post = Post.builder().user(user).content(dto.getContent()).comments(new ArrayList<>())
				.likes(new ArrayList<>()).image(dto.getImage()).build();
		postRepository.save(post);
	}

}
