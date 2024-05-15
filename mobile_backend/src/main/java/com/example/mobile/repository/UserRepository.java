package com.example.mobile.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import com.example.mobile.model.User;

@Component
public interface UserRepository extends JpaRepository<User, Integer> {
	public User findByName(String username);

	public User findByPhone(String phone);

	@Query("SELECT u FROM User u WHERE u NOT IN (SELECT eu FROM User eu WHERE eu IN :excludedUsers)")
	public List<User> findAllExcludeFriend(List<User> excludedUsers);
}
