package com.ssafy.db.repository;

import com.ssafy.db.entity.Book;
import com.ssafy.db.entity.Book_like;
import com.ssafy.db.entity.Book_review;
import com.ssafy.db.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 도서 리뷰 관련 모델에 쿼리를 생성하기 위함.
 */
@Repository
public interface BookReviewRepository extends JpaRepository<Book_review, Long> {
	
	List<Book_review> findAllByBook(Book book);
	List<Book_review> findAllByUser(User user);
    
}