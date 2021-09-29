package com.ssafy.db.entity;

import javax.persistence.Entity;

import lombok.Setter;

import lombok.Getter;

@Entity
@Getter
@Setter
public class Table_keyword extends BaseEntity{
	
	private String content;
}
