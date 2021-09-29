package com.ssafy.db.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Book_genre extends BaseEntity{
	
	@ManyToOne
	@JoinColumn(name = "book_bid")
	private Book book;
	
	@ManyToOne
	@JoinColumn(name = "genre_gid")
	private Table_genre genre;

}
