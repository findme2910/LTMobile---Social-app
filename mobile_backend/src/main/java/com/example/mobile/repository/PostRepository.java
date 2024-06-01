package com.example.mobile.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.example.mobile.model.Post;
import com.example.mobile.model.User;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer>{
	public Post save(Post post);
	@Query("SELECT p FROM Post p where p.user IN (SELECT u from User u WHERE u IN :friends) order by p.createAt desc ")
	public List<Post> findFriendPosts(List<User> friends);
}
