package com.ssafy.db.repository;

import com.ssafy.db.entity.Table_genre;
import com.ssafy.db.entity.Table_keyword;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 유저 모델 관련 디비 쿼리 생성을 위한 JPA Query Method 인터페이스 정의.
 */
@Repository
public interface TableKeywordRepository extends JpaRepository<Table_keyword, Long> {
	
    Optional<Table_keyword> findByContent(String content);
}
