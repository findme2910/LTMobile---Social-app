package com.example.mobile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.example.mobile.model.User;

@Component
public interface UserRepository extends JpaRepository<User, Integer> {
	public User findByName(String username);
}
