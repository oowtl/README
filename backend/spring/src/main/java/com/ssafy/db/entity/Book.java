package com.ssafy.db.entity;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

// 책 모델 정의
@Entity
@Getter
@Setter
public class Book extends BaseEntity{

	private String title;
	private String publisher;
	private String story;
	private Integer price;
	private String pday;
	private String img;
}
