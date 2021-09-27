package com.ssafy.db.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Book_tendency extends BaseEntity{
	
	@ManyToOne
	@JoinColumn(name = "user_uid")
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "book_bid")
	private Book book;

}
