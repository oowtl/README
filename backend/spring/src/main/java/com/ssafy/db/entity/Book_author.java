package com.ssafy.db.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Book_author extends BaseEntity{
	
	@ManyToOne
	@JoinColumn(name = "book_bid")
	private Book book;
	
	@ManyToOne
	@JoinColumn(name = "author_aid")
	private Table_author author_aid;
}
