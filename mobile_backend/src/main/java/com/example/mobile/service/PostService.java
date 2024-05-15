package com.example.mobile.service;

import com.example.mobile.dto.AddPostDTO;

public interface PostService {
	public void save(AddPostDTO dto) throws Exception;
}
