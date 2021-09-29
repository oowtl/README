package com.ssafy.db.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Common_detail extends BaseEntity{
	
	@ManyToOne
	private Common com;
	
	private String common_detail;

}
