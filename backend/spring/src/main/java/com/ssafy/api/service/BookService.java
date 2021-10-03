package com.ssafy.api.service;

import java.util.ArrayList;
import java.util.List;

import com.ssafy.api.request.BookReviewPostReq;
import com.ssafy.api.request.UserDuplicatedReq;
import com.ssafy.api.request.UserMbtiReq;
import com.ssafy.api.request.UserRegisterPostReq;
import com.ssafy.db.entity.Book;
import com.ssafy.db.entity.Book_like;
import com.ssafy.db.entity.Book_review;
import com.ssafy.db.entity.User;

/**
 *	유저 관련 비즈니스 로직 처리를 위한 서비스 인터페이스 정의.
 */
public interface BookService {
	
	// 도서 정보 반환
	Book getBookDetail(Long bookId);
	
	// 리뷰
	Book_review saveReview(User user, Long bookId, BookReviewPostReq bookReviewInfo);
	// 도서 별 리뷰 조회
	List<Book_review> getBookReviewList (Book book);
	
	
	// 좋아요
	List<Book_like> getUserLike(User user, Long bookId);
	Book_like saveLike(User user, Long bookId);
	Boolean deleteLike(User user, Long bookId);
	// 좋아요 조회
	List<Book_like> getBookLikeList (Book book);
}
