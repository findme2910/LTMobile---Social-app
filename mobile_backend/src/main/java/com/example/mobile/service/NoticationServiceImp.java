package com.example.mobile.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.mobile.dto.RequestNotificationDTO;
import com.example.mobile.model.Notification;
import com.example.mobile.model.Post;
import com.example.mobile.model.User;
import com.example.mobile.repository.NotificationRepository;
import com.example.mobile.repository.UserRepository;

import lombok.AllArgsConstructor;
// triển khai api nội bộ để rest có thể gọi
@Service
@AllArgsConstructor
public class NoticationServiceImp implements NotificationService {
	private final NotificationRepository notificationRepository;
	private final UserRepository userRepository;
	private AuthStaticService authStaticService;

	@Override
	public void likeNoti(Post to) {
		// TODO Auto-generated method stub
		User currUser = authStaticService.currentUser();
		Notification notification = Notification.builder()
				.trigger(currUser)
				.content("đã thích bài viết của bạn.")
				.post(to)
				.build();
		User postOwner = to.getUser();
		postOwner.getNotifications().add(notification);
		notificationRepository.save(notification);
		userRepository.save(postOwner);
	}

	@Override
	public void commentNoti(Post post) {
		// TODO Auto-generated method stub
		User currUser = authStaticService.currentUser();
		Notification notification = Notification.builder()
				.trigger(currUser)
				.content("đã bình luận trên bài viết của bạn.")
				.post(post)
				.build();
		User postOwner = post.getUser();
		postOwner.getNotifications().add(notification);
		notificationRepository.save(notification);
		userRepository.save(postOwner);
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
		//lấy hết tất cả notification trong một user ra
		List<Notification> notifications = authStaticService.currentUser().getNotifications();
		int startIndex = dto.getNext() * 10; //sau đó lấy ra số lượng thông báo cho từng trang
		if (startIndex >= notifications.size()) return new ArrayList<>();
		int endIndex = Math.min(startIndex + 10, notifications.size());
		return notifications.subList(startIndex, endIndex);
	}
}
