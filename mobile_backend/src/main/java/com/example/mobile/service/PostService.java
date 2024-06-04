package com.example.mobile.service;

import java.util.List;

import com.example.mobile.dto.AddPostDTO;
import com.example.mobile.model.Post;

public interface PostService {
	public void save(AddPostDTO dto) throws Exception;

	public List<Post> get();
}
