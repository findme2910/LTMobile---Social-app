package com.example.mobile.service;

import java.util.List;

import com.example.mobile.dto.AddUserDTO;
import com.example.mobile.dto.CommentDTO;
import com.example.mobile.dto.FriendRequestDTO;
import com.example.mobile.dto.LikeDTO;
import com.example.mobile.dto.LoginDTO;
import com.example.mobile.dto.UserUpdateDTO;
import com.example.mobile.model.FriendRequest;
import com.example.mobile.model.User;

public interface UserService {
	public void save(AddUserDTO dto) throws Exception;

	public String login(LoginDTO loginDTO) throws Exception;

	public void like(LikeDTO dto) throws Exception;

	public void comment(CommentDTO dto) throws Exception;

	public void update(UserUpdateDTO dto) throws Exception;

	public List<User> findAllAcceptFriend();

	public void handleFriendRequest(FriendRequestDTO dto) throws Exception;

	public List<FriendRequest> getAllFriendRequest() throws Exception;

	public void handleAccpetFriendRequest(FriendRequestDTO dto) throws Exception;

	public void handleRejectFriendRequest(FriendRequestDTO dto) throws Exception;

	public List<User> getSuggestAddFriend();

	public void handleCancelFriendRequest(FriendRequestDTO dto) throws Exception;

}
