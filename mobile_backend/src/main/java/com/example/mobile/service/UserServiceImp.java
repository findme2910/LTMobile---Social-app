package com.example.mobile.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.mobile.config.ConvertFile;
import com.example.mobile.config.DefaultSource;
import com.example.mobile.config.security.JwtUtils;
import com.example.mobile.dto.AddUserDTO;
import com.example.mobile.dto.CommentDTO;
import com.example.mobile.dto.FriendRequestDTO;
import com.example.mobile.dto.LikeDTO;
import com.example.mobile.dto.LoginDTO;
import com.example.mobile.dto.UserUpdateDTO;
import com.example.mobile.model.Comment;
import com.example.mobile.model.FriendRequest;
import com.example.mobile.model.Like;
import com.example.mobile.model.Post;
import com.example.mobile.model.User;
import com.example.mobile.repository.CommentRepository;
import com.example.mobile.repository.FriendRequestRepository;
import com.example.mobile.repository.LikeRepository;
import com.example.mobile.repository.PostRepository;
import com.example.mobile.repository.UserRepository;

import jakarta.persistence.EntityManager;

@Service
public class UserServiceImp implements UserService {
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	UserRepository userRepository;
	@Autowired
	PostRepository postRepository;
	@Autowired
	LikeRepository likeRepository;
	@Autowired
	FriendRequestRepository friendRequestRepository;
	@Autowired
	CommentRepository commentRepository;
	@Autowired
	EntityManager entityManager;
	@Autowired
	JwtUtils jwtUtils;

	@Override
	public void save(AddUserDTO dto) throws Exception {
		if (userRepository.findByPhone(dto.getPhone()) != null)
			throw new Exception("Phone exits");
		User u = User.builder().phone(dto.getPhone()).name(dto.getName())
				.password(passwordEncoder.encode(dto.getPassword())).comments(new ArrayList<>())
				.likes(new ArrayList<>()).friends(new ArrayList<>()).posts(new ArrayList<>())
				.birth(new Date(dto.getBirth())).avatar(ConvertFile.toBlob(DefaultSource.DEFAULT_AVATAR)).build();

		userRepository.save(u);
	}

	@Override
	public String login(LoginDTO loginDTO) throws Exception {
		User user = userRepository.findByPhone(loginDTO.phone);

		if (passwordEncoder.matches(loginDTO.password, user.getPassword())) {
			return jwtUtils.gennerateJwtToken(loginDTO.phone);
		} else {
			throw new Exception("Login Error");
		}
	}

	@Override
	public void like(LikeDTO dto) throws Exception {
		Like like = likeRepository.findByUser_IdAndPost_Id(dto.getUserId(), dto.getPostId());
		if (like != null) {
			likeRepository.delete(like);
		} else {
			User user = userRepository.findById(dto.getUserId()).orElseThrow();
			Post post = postRepository.findById(dto.getPostId()).orElseThrow();
			like = Like.builder().user(user).post(post).createAt(new Date()).build();
		}
	}

	@Override
	public void comment(CommentDTO dto) throws Exception {
		User user = userRepository.findById(dto.getUserId()).orElseThrow();
		Post post = postRepository.findById(dto.getPostId()).orElseThrow();

		Comment comment = Comment.builder().user(user).post(post).content(dto.getContent()).updateAt(new Date())
				.createAt(new Date()).build();

		commentRepository.save(comment);
	}

	@Override
	public void update(UserUpdateDTO dto) throws Exception {

	}

	@Override
	public List<User> findAllAcceptFriend() {
		return userRepository.findAllExcludeFriend(Arrays.asList(findCurrentUser()));
	}

	public User findCurrentUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = (User) authentication.getPrincipal();
		return userRepository.findById(user.getId()).get();
	}

	@Override
	public void handleFriendRequest(FriendRequestDTO dto) throws Exception {
		User current = findCurrentUser();
		Optional<User> toUser = userRepository.findById(dto.getUserId());
		if (toUser.isPresent()) {
			if (toUser.get().friended(current.getId())) {
				throw new Exception("To User is your friend");
			}
			if (toUser.get().requested(current.getId())) {
				throw new Exception("Requested to this user");
			}
			var friendRequest = FriendRequest.builder().fromUser(current).createAt(new Date()).build();
			toUser.get().getFriendRequests().add(friendRequest);
			userRepository.save(toUser.get());
		} else {
			throw new Exception("To User is not present");
		}
	}

	@Override
	public List<FriendRequest> getAllFriendRequest() throws Exception {

		return findCurrentUser().getFriendRequests();
	}

	@Override
	public void handleAccpetFriendRequest(FriendRequestDTO dto) throws Exception {
		User user = findCurrentUser();
		
		List<FriendRequest> friendRequests = user.getFriendRequests();
		Iterator<FriendRequest> it = friendRequests.iterator();
		User accept = null;
		while (it.hasNext()) {
			accept = userRepository.findById(it.next().getFromUser().getId()).get();
			if (accept.getId() == dto.getUserId()) {
				user.getFriends().add(accept);
				accept.getFriends().add(user); 
				userRepository.save(accept);
//				userRepository.save(user);
				it.remove();
				System.out.println("ok");
				return;
			}
		}
		throw new Exception("Invalid userId");
	}

	@Override
	public void handleRejectFriendRequest(FriendRequestDTO dto) throws Exception {
		User user = findCurrentUser();
		List<FriendRequest> friendRequests = user.getFriendRequests();
		Iterator<FriendRequest> it = friendRequests.iterator();
		User reject = null;
		while (it.hasNext()) {
			reject = it.next().getFromUser();
			if (reject.getId() == dto.getUserId()) {
				it.remove();
				userRepository.save(user);
				return;
			}
		}
		throw new Exception("Don't have request for this user");
	}

	@Override
	public List<User> getSuggestAddFriend() {
		User current = findCurrentUser();
		List<User> exclude = new ArrayList<>(current.getFriends());
		current.getFriendRequests().forEach(n -> exclude.add(n.getFromUser()));
		System.out.println(exclude.size());
		return userRepository.findAllExcludeFriend(exclude);
	}

	@Override
	public void handleCancelFriendRequest(FriendRequestDTO dto) throws Exception {
		User current = findCurrentUser();
		System.out.println(dto.getUserId());
		System.out.println(current.getFriends().get(0).getId());
		if(current.friended(dto.getUserId())) {
			User cancelFriend = userRepository.findById(dto.getUserId()).get();
			
			Iterator<User> friendOfCurrentUser = current.getFriends().iterator();
			Iterator<User> friendOfCancelUser = cancelFriend.getFriends().iterator();
			
			while(friendOfCancelUser.hasNext()) {
				if(friendOfCancelUser.next().getId() == current.getId()) {
					friendOfCancelUser.remove();
					break;
				}
			}
			while(friendOfCurrentUser.hasNext()) {
				if(friendOfCurrentUser.next().getId() == cancelFriend.getId()) {
					friendOfCurrentUser.remove();
					break;
				}
			}
			userRepository.save(current);
			userRepository.save(cancelFriend);
		}
		throw new Exception("Invalid userId");
	}
}
