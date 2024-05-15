package com.example.mobile.model;

import java.util.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Getter
@Setter
public class FriendRequest {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int id;
	@OneToOne(cascade = CascadeType.DETACH)
	public User fromUser;
	public Date createAt;
}
