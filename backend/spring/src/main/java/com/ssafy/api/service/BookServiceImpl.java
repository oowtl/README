package com.ssafy.api.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ssafy.api.request.BookReviewPostReq;
import com.ssafy.api.request.UserDuplicatedReq;
import com.ssafy.api.request.UserMbtiReq;
import com.ssafy.api.request.UserRegisterPostReq;
import com.ssafy.db.entity.Book;
import com.ssafy.db.entity.Book_author;
import com.ssafy.db.entity.Book_genre;
import com.ssafy.db.entity.Book_like;
import com.ssafy.db.entity.Book_review;
import com.ssafy.db.entity.Book_tendency;
import com.ssafy.db.entity.Common;
import com.ssafy.db.entity.Common_detail;
import com.ssafy.db.entity.Table_author;
import com.ssafy.db.entity.Table_genre;
import com.ssafy.db.entity.User;
import com.ssafy.db.repository.BookAuthorRepository;
import com.ssafy.db.repository.BookGenreRepository;
import com.ssafy.db.repository.BookLikeRepository;
import com.ssafy.db.repository.BookRepository;
import com.ssafy.db.repository.BookReviewRepository;
import com.ssafy.db.repository.BookTendencyRepository;
import com.ssafy.db.repository.CommonDetailRepository;
import com.ssafy.db.repository.CommonRepository;
import com.ssafy.db.repository.TableAuthorRepository;
import com.ssafy.db.repository.TableGenreRepository;
import com.ssafy.db.repository.UserRepository;
import com.ssafy.db.repository.UserRepositorySupport;

/**
 *	도서 관련 비즈니스 로직 처리를 위한 서비스 구현 정의.
 */
@Service("bookService")
public class BookServiceImpl implements BookService {
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	BookRepository bookRepository;
	
	@Autowired
	BookGenreRepository bookGenreRepository;
	
	@Autowired
	BookAuthorRepository bookAuthorRepository;
	
	@Autowired
	BookLikeRepository bookLikeRepository;
	
	@Autowired
	BookReviewRepository bookReviewRepository;
	
	@Autowired
	BookTendencyRepository bookTendencyRepository;
	
	@Autowired
	TableGenreRepository tableGenreRepository;
	
	@Autowired
	TableAuthorRepository tableAuthorRepository;
	
	@Autowired
	CommonRepository commonRepository;
	
	@Autowired
	CommonDetailRepository commonDetailRepository;
	
	@Autowired
	UserRepositorySupport userRepositorySupport;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	
	@Override
	public Book getBookDetail(Long bookId) {
		// TODO Auto-generated method stub
		
		Book book = bookRepository.findById(bookId).get();
		return book;
	}
	
	
	@Override
	public Book_review saveReview(User user, Long bookId, BookReviewPostReq bookReviewInfo) {
		// TODO Auto-generated method stub
		
		Book book = bookRepository.findById(bookId).get();
		Book_review review = new Book_review();
		review.setPoint(bookReviewInfo.getScore());
		review.setContent(bookReviewInfo.getContent());
		review.setBook(book);
		review.setUser(user);
		
		return bookReviewRepository.save(review);
	}
	
	@Override
	public List<Book_review> getBookReviewList(Book book) {
		// TODO Auto-generated method stub
		List<Book_review> ReviewList = bookReviewRepository.findAllByBook(book);
		
		return ReviewList;
	}
	
	@Override
	public List<Book_like> getUserLike(User user, Long bookId) {
		// TODO Auto-generated method stub
		
		Book book = bookRepository.findById(bookId).orElseThrow(null);
		List<Book_like> existLike = bookLikeRepository.findAllByUserAndBook(user, book);
		
		return existLike;
	}
	
	
	@Override
	public Book_like saveLike(User user, Long bookId) {
		// TODO Auto-generated method stub
		
		Book book = bookRepository.findById(bookId).get();
		Book_like like = new Book_like();
		like.setBook(book);
		like.setUser(user);
	
		return bookLikeRepository.save(like);
	}
	
	@Override
	public Boolean deleteLike(User user, Long bookId) {
		// TODO Auto-generated method stub
		
		Book book = bookRepository.findById(bookId).get();
		List<Book_like> likeList = bookLikeRepository.findAllByUserAndBook(user, book);
		
		bookLikeRepository.delete(likeList.get(0));
		
		return true;
	}
	
	@Override
	public List<Book_like> getBookLikeList(Book book) {
		// TODO Auto-generated method stub
		
		List<Book_like> likeList = bookLikeRepository.findAllByBook(book);
		
		return likeList;
	}
}
