package com.ssafy.db.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class User_tendency extends BaseEntity{
	
	@OneToOne
	@JoinColumn(name = "user_uid")
	private User user;
	
	private String user_genre;
	private String user_author;
	private String user_publisher;
	private String user_keyword;

}
