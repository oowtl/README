package com.ssafy.db.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Setter;

import lombok.Getter;

@Entity
@Getter
@Setter
public class Book_keyword extends BaseEntity{

	@ManyToOne
	@JoinColumn(name = "book_bid")
	private Book book;
	
	@ManyToOne
	@JoinColumn(name = "keyword_kid")
	private Table_keyword keyword;
}
