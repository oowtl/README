package com.ssafy.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.db.entity.Common;

@Repository
public interface CommonRepository extends JpaRepository<Common, Long>{
	
	// 공통 키워드
	Common findByName(String commonKey);

}
