package com.example.mobile.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.mobile.dto.RequestNotificationDTO;
import com.example.mobile.model.Notification;
import com.example.mobile.model.Post;
import com.example.mobile.model.User;
import com.example.mobile.repository.NotificationRepository;
import com.example.mobile.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class NoticationServiceImp implements NotificationService {
	private final NotificationRepository notificationRepository;
	private final UserRepository userRepository;
	private AuthStaticService authStaticService;

	@Override
	public void likeNoti(Post to) {
		// TODO Auto-generated method stub

	}

	@Override
	public void commentNoti(Post post) {
		// TODO Auto-generated method stub

	}

	@Override
	public void requestAddFriend(User to) {
		User currUser = authStaticService.currentUser();
		Notification notification = Notification.builder().trigger(currUser).content("Đã gửi yêu cầu kết bạn").build();
		to.getNotifications().add(notification);
		notificationRepository.save(notification);
		userRepository.save(to);
	}

	@Override
	public void acceptionAddFriend(User to) {
		User currUser = authStaticService.currentUser();
		Notification notification = Notification.builder().trigger(currUser).content("Đã chấp nhận lời mời kết bạn")
				.build();
		notificationRepository.save(notification);
		to.getNotifications().add(notification);
		notificationRepository.save(notification);
		userRepository.save(to);
	}

	@Override
	public List<Notification> getNotis(RequestNotificationDTO dto) {
		// TODO Auto-generated method stub
		User curr = authStaticService.currentUser();
		List<Notification> notifications = authStaticService.currentUser().getNotifications();
		curr.setCurrentNoti(curr.getNotifications().size());
		userRepository.save(curr);
		if (dto.getNext() * 10 >= notifications.size())
			return null;
		return authStaticService.currentUser().getNotifications().subList(dto.getNext() * 10,
				Math.min(dto.getNext() * 10 + 10, notifications.size()));
	}
}
