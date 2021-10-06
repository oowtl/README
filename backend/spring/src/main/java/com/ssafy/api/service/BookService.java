package com.ssafy.api.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.ssafy.api.request.BookReviewPostReq;
import com.ssafy.api.request.UserDuplicatedReq;
import com.ssafy.api.request.UserMbtiReq;
import com.ssafy.api.request.UserRegisterPostReq;
import com.ssafy.db.entity.Book;
import com.ssafy.db.entity.Book_genre;
import com.ssafy.db.entity.Book_keyword;
import com.ssafy.db.entity.Book_like;
import com.ssafy.db.entity.Book_review;
import com.ssafy.db.entity.Table_genre;
import com.ssafy.db.entity.Table_keyword;
import com.ssafy.db.entity.User;

/**
 *	유저 관련 비즈니스 로직 처리를 위한 서비스 인터페이스 정의.
 */
public interface BookService {
	
	// 도서 정보 반환 ( 상세정보로 만들어 주기 )
	Book getBookDetail(Long bookId);
	
	// 도서 목록
	// 장르
	Optional<Table_genre> getGenre (String genre);
	// 장르에 해당하는 도서 목록 반환
	List<Book_genre> findGenreBook (Table_genre genre);
	// 장르별 도서정보 목록 반환
	List<HashMap<String, Object>> getGenBookList(List<Book_genre> genBook);
	// 주제어
	Optional<Table_keyword> getKeyword (String keyword);
	// 주제어에 해당하는 book_keyword 반환
	List<Book_keyword> findKeywordBook(Table_keyword keyword);
	// 주제어별 도서정보 목록 반환
	List<HashMap<String, Object>> getKeywordBookList(List<Book_keyword> keyBook);
	// 도서 장르 추출(set)
	List<String> getBooktoGenre(Set<Book> bookList);
	// Response로 변환하기
	List<HashMap<String, Object>> getBookResponse(List<Book> bookList);
	
	// 리뷰
	Book_review saveReview(User user, Long bookId, BookReviewPostReq bookReviewInfo);
	// 도서 별 리뷰 조회
	List<HashMap<String, Object>> getBookReviewList (Book book);
	// 도서 상세정보 리뷰 카운트
	Integer getReviewCnt (Book book);
	// 유저 작성 리뷰 조회
	List<Book_review> getUserReviewList (User user); 
	
	// 좋아요
	List<Book_like> getUserLike(User user, Long bookId);
	Book_like saveLike(User user, Long bookId);
	Boolean deleteLike(User user, Long bookId);
	// 좋아요 조회
	List<HashMap<String, Object>> getBookLikeList (Book book);
	// 좋아요 리뷰 카운트
	Integer getLikeCnt (Book book);
	// 유저 좋아요 조회
	Set<Book> getUserLikeList(User user);
	
	
	// 추천하기 (Book)
	List<Book> getRecommendBooks (List<String> genreList, Set<Book> likeBookList);

}
