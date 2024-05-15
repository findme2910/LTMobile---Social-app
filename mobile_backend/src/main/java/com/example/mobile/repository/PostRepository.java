package com.example.mobile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.example.mobile.model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer>{
	public Post save(Post post);
}
