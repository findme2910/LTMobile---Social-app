package com.example.mobile.model;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String content;
	private String image;

	private Date createAt;
	private Date updateAt;
	
	@ManyToOne
	@JsonBackReference 
	private User user;
	
	@OneToMany
	private List<Like> likes; 
	@OneToMany
	private List<Comment> comments;
}
