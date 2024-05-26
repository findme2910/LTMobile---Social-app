package com.example.mobile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mobile.dto.RequestNotificationDTO;
import com.example.mobile.mapper.UserMapper;
import com.example.mobile.service.NotificationService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/noti")
public class NotificationController {
	@Autowired
	private NotificationService notificationService;

	@GetMapping
	public ResponseEntity<?> getNotifications(@RequestBody RequestNotificationDTO dto) {
		return ResponseEntity
				.ok(notificationService.getNotis(dto).stream().map(UserMapper.INSTANCE::NotificationToDTO).toList());
	}
}
