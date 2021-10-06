package com.ssafy.api.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

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
import com.ssafy.db.entity.Book_keyword;
import com.ssafy.db.entity.Book_like;
import com.ssafy.db.entity.Book_review;
import com.ssafy.db.entity.Book_tendency;
import com.ssafy.db.entity.Common;
import com.ssafy.db.entity.Common_detail;
import com.ssafy.db.entity.Table_author;
import com.ssafy.db.entity.Table_genre;
import com.ssafy.db.entity.Table_keyword;
import com.ssafy.db.entity.User;
import com.ssafy.db.repository.BookAuthorRepository;
import com.ssafy.db.repository.BookGenreRepository;
import com.ssafy.db.repository.BookKeywordRepository;
import com.ssafy.db.repository.BookLikeRepository;
import com.ssafy.db.repository.BookRepository;
import com.ssafy.db.repository.BookReviewRepository;
import com.ssafy.db.repository.BookTendencyRepository;
import com.ssafy.db.repository.CommonDetailRepository;
import com.ssafy.db.repository.CommonRepository;
import com.ssafy.db.repository.TableAuthorRepository;
import com.ssafy.db.repository.TableGenreRepository;
import com.ssafy.db.repository.TableKeywordRepository;
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
	BookKeywordRepository bookKeywordRepository;
	
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
	TableKeywordRepository tableKeywordRepository;
	
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
	public Optional<Table_genre> getGenre(String genre) {
		// TODO Auto-generated method stub

		return tableGenreRepository.findByGenre(genre);
	}
	
	@Override
	public List<Book_genre> findGenreBook(Table_genre genre) {
		// TODO Auto-generated method stub
		
		return bookGenreRepository.findFirst20ByGenre(genre);
	}
		
	@Override
	public List<HashMap<String, Object>> getGenBookList(List<Book_genre> genBook) {
		// TODO Auto-generated method stub
		
		List<HashMap<String, Object>> bookList = new ArrayList<HashMap<String,Object>>();
		
		genBook.forEach((gb) -> {
			Book book = bookRepository.findOneById(gb.getBook().getId()).get(); // 이미 존재하는 것
			
			HashMap<String, Object> bookInfo = new HashMap<String, Object>();
			bookInfo.put("id", book.getId());
			bookInfo.put("title", book.getTitle());
			bookInfo.put("publisher", book.getPublisher());
			bookInfo.put("story", book.getStory());
			bookInfo.put("img", book.getImg());
			bookInfo.put("price", book.getPrice());
			
			Book_author bAuthor = bookAuthorRepository.findOneByBook(book); // Book_author
			Table_author tAuthor = tableAuthorRepository.findOneById(bAuthor.getAuthor_aid().getId()); // Table_author
			bookInfo.put("author", tAuthor.getAuthor());
			
			List<Book_genre> bookGenList = bookGenreRepository.findAllByBook(book);
			List<String> genList = new ArrayList<String>();
			bookGenList.forEach((gen) -> {
				genList.add(gen.getGenre().getGenre());
			});			
			bookInfo.put("genre", genList);
			
			List<Book_keyword> bookKeyList = bookKeywordRepository.findAllByBook(book);
			List<String> keyList = new ArrayList<String>();
			bookKeyList.forEach((bkey) -> {
				keyList.add(bkey.getKeyword().getContent());
			});
			bookInfo.put("topic", keyList);
			
			List<Book_review> reviewList = bookReviewRepository.findAllByBook(book);
			bookInfo.put("review_cnt", reviewList.size());
			
			List<Book_like> likeList = bookLikeRepository.findAllByBook(book);
			bookInfo.put("like_cnt", likeList.size());
			
			bookList.add(bookInfo);
		});
		
		return bookList;
	}
	
	@Override
	public List<Book_keyword> findKeywordBook(Table_keyword keyword) {
		// TODO Auto-generated method stub
		return bookKeywordRepository.findFirst20ByKeyword(keyword);
	}
	
	@Override
	public Optional<Table_keyword> getKeyword(String keyword) {
		// TODO Auto-generated method stub
		
		return tableKeywordRepository.findByContent(keyword);
	}
	
	@Override
	public List<HashMap<String, Object>> getKeywordBookList(List<Book_keyword> keyBook) {
		// TODO Auto-generated method stub
		
		List<HashMap<String, Object>> bookList = new ArrayList<HashMap<String,Object>>();
		
		keyBook.forEach((key) -> {
			Book book = bookRepository.findById(key.getBook().getId()).get();
			
			HashMap<String, Object> bookInfo = new HashMap<String, Object>();
			bookInfo.put("id", book.getId());
			bookInfo.put("title", book.getTitle());
			bookInfo.put("publisher", book.getPublisher());
			bookInfo.put("story", book.getStory());
			bookInfo.put("img", book.getImg());
			bookInfo.put("price", book.getPrice());
			
			Book_author bAuthor = bookAuthorRepository.findOneByBook(book); // Book_author
			Table_author tAuthor = tableAuthorRepository.findOneById(bAuthor.getAuthor_aid().getId()); // Table_author
			bookInfo.put("author", tAuthor.getAuthor());
			
			List<Book_genre> bookGenList = bookGenreRepository.findAllByBook(book);
			List<String> genList = new ArrayList<String>();
			bookGenList.forEach((gen) -> {
				genList.add(gen.getGenre().getGenre());
			});			
			bookInfo.put("genre", genList);
			
			List<Book_keyword> bookKeyList = bookKeywordRepository.findAllByBook(book);
			List<String> keyList = new ArrayList<String>();
			bookKeyList.forEach((bkey) -> {
				keyList.add(bkey.getKeyword().getContent());
			});
			bookInfo.put("topic", keyList);
			
			List<Book_review> reviewList = bookReviewRepository.findAllByBook(book);
			bookInfo.put("review_cnt", reviewList.size());
			
			List<Book_like> likeList = bookLikeRepository.findAllByBook(book);
			bookInfo.put("like_cnt", likeList.size());
			
			bookList.add(bookInfo);
		});
		
		return bookList;
	}
	
	@Override
	public List<String> getBooktoGenre(Set<Book> bookList) {
		// TODO Auto-generated method stub
		
		Set<String> genreSet = new HashSet<String>();
		List<String> genreList = new ArrayList<String>();
		
		bookList.forEach((book) -> {
			List<Book_genre> bgList = bookGenreRepository.findAllByBook(book);
			bgList.forEach((bg) -> {
				genreSet.add(bg.getGenre().getGenre());
			});
		});
		genreList.addAll(genreSet);
		
		return genreList;
	}
	
	@Override
	public List<HashMap<String, Object>> getBookResponse(List<Book> bookList) {
		// TODO Auto-generated method stub
		
		List<HashMap<String, Object>> resBookList = new ArrayList<HashMap<String,Object>>();
		
		bookList.forEach((book) -> {
			HashMap<String, Object> bookInfo = new HashMap<String, Object>();
			bookInfo.put("id", book.getId());
			bookInfo.put("title", book.getTitle());
			bookInfo.put("publisher", book.getPublisher());
			bookInfo.put("story", book.getStory());
			bookInfo.put("img", book.getImg());
			bookInfo.put("price", book.getPrice());
			
			Book_author bAuthor = bookAuthorRepository.findOneByBook(book); // Book_author
			Table_author tAuthor = tableAuthorRepository.findOneById(bAuthor.getAuthor_aid().getId()); // Table_author
			bookInfo.put("author", tAuthor.getAuthor());
			
			List<Book_genre> bookGenList = bookGenreRepository.findAllByBook(book);
			List<String> genList = new ArrayList<String>();
			bookGenList.forEach((gen) -> {
				genList.add(gen.getGenre().getGenre());
			});			
			bookInfo.put("genre", genList);
			
			List<Book_keyword> bookKeyList = bookKeywordRepository.findAllByBook(book);
			List<String> keyList = new ArrayList<String>();
			bookKeyList.forEach((bkey) -> {
				keyList.add(bkey.getKeyword().getContent());
			});
			bookInfo.put("topic", keyList);
			
			List<Book_review> reviewList = bookReviewRepository.findAllByBook(book);
			bookInfo.put("review_cnt", reviewList.size());
			
			List<Book_like> likeList = bookLikeRepository.findAllByBook(book);
			bookInfo.put("like_cnt", likeList.size());
			
			resBookList.add(bookInfo);
		});
		
		return resBookList;
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
	public List<HashMap<String, Object>> getBookReviewList(Book book) {
		// TODO Auto-generated method stub
		List<Book_review> reviewList = bookReviewRepository.findAllByBook(book);
		
		List<HashMap<String, Object>> changeReview = new ArrayList<HashMap<String, Object>>();
		
		if (reviewList.isEmpty()) {
			return changeReview;
		} else {			
			reviewList.forEach((review) -> {
				HashMap<String, Object> reviewInfo = new HashMap<String, Object>();
				reviewInfo.put("id", review.getId());
				reviewInfo.put("userId", review.getUser().getUserId());
				reviewInfo.put("score", review.getPoint());
				reviewInfo.put("content", review.getContent());
				
				changeReview.add(reviewInfo);
			});
			return changeReview;
		}
		
	}
	
	@Override
	public Integer getReviewCnt(Book book) {
		// TODO Auto-generated method stub
		
		return bookReviewRepository.findAllByBook(book).size();
	}
	
	@Override
	public List<Book_review> getUserReviewList(User user) {
		// TODO Auto-generated method stub
		
		return bookReviewRepository.findAllByUser(user);
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
	public List<HashMap<String, Object>> getBookLikeList(Book book) {
		// TODO Auto-generated method stub
		
		List<Book_like> likeList = bookLikeRepository.findAllByBook(book);
		
		List<HashMap<String, Object>> changeLike = new ArrayList<HashMap<String, Object>>();
		
		if (likeList.isEmpty()) {
			return changeLike;
		} else {
			likeList.forEach((like) -> {
				HashMap<String, Object> likeInfo = new HashMap<String, Object>();
				likeInfo.put("id", like.getId());
				likeInfo.put("userId", like.getUser().getUserId());
				changeLike.add(likeInfo);
			});
			return changeLike;
		}
	}
	@Override
	public Integer getLikeCnt(Book book) {
		// TODO Auto-generated method stub
		return bookReviewRepository.findAllByBook(book).size();
	}
	
	@Override
	public Set<Book> getUserLikeList(User user) {
		// TODO Auto-generated method stub
		
		List<Book_like> likeList = bookLikeRepository.findAllByUser(user);
		Set<Book> bookList = new HashSet<Book>();
		
		likeList.forEach((like) -> {
			bookList.add(like.getBook());
		});
		
		return bookList;
	}
	
	// 추천하기
	@Override
	public List<Book> getRecommendBooks(List<String> genreList, Set<Book> likeBookList) {
		// TODO Auto-generated method stub
		
		
		// 1. 장르리스트에 들어온것이 5미만, 5, 5 초과
		// 2. 랜덤으로 장르 선택
		// 3. 장르별로 20개 First 로 들고와서 user 좋아요와 비교한다.
		// 4. 포함되지 않는 것으로 각 장르별 4개씩 들고온다
		Integer gListsize = genreList.size();
		if (gListsize > 10) {
			// 장르 5개 선택
			Random rd = new Random();
			Set<String> gSet = new HashSet<String>(); // 장르 5개 선택
			while (gSet.size() < 5) {
				gSet.add(genreList.get(rd.nextInt(genreList.size())));
			}
			// 장르별 검색 리스트
			List<Table_genre> tgList = new ArrayList<Table_genre>();
			gSet.forEach((gs) -> {
				tgList.add(tableGenreRepository.findByGenre(gs).get());
			});
			// 장르별 검색
			List<Book> recomBook = new ArrayList<Book>();
			tgList.forEach((tg) -> {
				List<Book_genre> bgList = bookGenreRepository.findFirst20ByGenre(tg);
				
				if (bgList.size() >= 10) {
					Set<Book> bgSet = new HashSet<Book>();
					while (bgSet.size() < 5) {
						Book nBook = bgList.get(rd.nextInt(bgList.size())).getBook();
						if (!likeBookList.contains(nBook)) {
							bgSet.add(nBook);
						}
					}
					recomBook.addAll(bgSet);
				} else {
					Integer listNum = 0;
					for (int i = 0; i < bgList.size(); i++) {
						Book nBook = bgList.get(i).getBook();
						if (listNum > 5) {
							break;
						}
						if (likeBookList.contains(nBook)) {
							continue;
						}
						recomBook.add(nBook);
						listNum ++;
					}					
				}
			});
			return recomBook;
		} else { // gListsize <= 5
			
			// genre 받아와서 쭉 넣어준다.
			Random rd = new Random();
			// 몇개를 받아와야할지 정한다.
			Integer bookQuantity = (25/genreList.size()) + 1;
			
			List<Table_genre> tgList = new ArrayList<Table_genre>();
			genreList.forEach((gl) -> {
				tgList.add(tableGenreRepository.findByGenre(gl).get());
			});
			
			List<Book> recomBook = new ArrayList<Book>();
			
			tgList.forEach((tg) -> {
				List<Book_genre> bgList = bookGenreRepository.findFirst20ByGenre(tg);
				if (bgList.size() >= 10) {
					Set<Book> bgSet = new HashSet<Book>();
					while (bgSet.size() < bookQuantity) {
						Book nBook = bgList.get(rd.nextInt(bgList.size())).getBook();
						if (!likeBookList.contains(nBook)) {
							bgSet.add(nBook);
						}
					}
					recomBook.addAll(bgSet);
				} else {
					Integer listNum = 0;
					for (int i = 0; i < bgList.size(); i++) {
						Book nBook = bgList.get(i).getBook();
						if (listNum > bookQuantity) {
							break;
						}
						if (likeBookList.contains(nBook)) {
							continue;
						}
						recomBook.add(nBook);
						listNum ++;
					}					
				}
			});
			return recomBook;
		}
	}
}
