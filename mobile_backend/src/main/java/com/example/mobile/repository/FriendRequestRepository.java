package com.example.mobile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.mobile.model.FriendRequest;

@Repository
public interface FriendRequestRepository extends JpaRepository<FriendRequest, Integer> {
	
}
