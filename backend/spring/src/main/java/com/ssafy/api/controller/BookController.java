package com.ssafy.api.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.aspectj.apache.bcel.classfile.Code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.api.request.BookListGetReq;
import com.ssafy.api.request.BookReviewPostReq;
import com.ssafy.api.response.BookDetailRes;
import com.ssafy.api.response.BookListRes;
import com.ssafy.api.service.BookService;
import com.ssafy.api.service.UserService;
import com.ssafy.common.auth.SsafyUserDetails;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.Book;
import com.ssafy.db.entity.Book_genre;
import com.ssafy.db.entity.Book_keyword;
import com.ssafy.db.entity.Book_like;
import com.ssafy.db.entity.Book_review;
import com.ssafy.db.entity.Table_genre;
import com.ssafy.db.entity.Table_keyword;
import com.ssafy.db.entity.User;
import com.ssafy.db.repository.BookLikeRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import springfox.documentation.annotations.ApiIgnore;

// 도서관련 API 요청 처리를 위한 컨트롤러
@Api(value = "도서 API", tags = {"Book"})
@RestController
@RequestMapping("/book")
public class BookController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	BookService bookService;
	
	@GetMapping("/detail/{bookId}")
	@ApiOperation(value = "도서 상세정보", notes = "도서 상세 정보를 반환한다.")	
	@ApiResponses({
		@ApiResponse(code = 200, message = "성공"),
		@ApiResponse(code = 500, message = "서버 오류"),
	})
	public ResponseEntity<BookDetailRes> getBookdetail (
			@PathVariable("bookId") Long bookId ) {
		
		try {
			Book book = bookService.getBookDetail(bookId);
			List<HashMap<String, Object>> reviews = bookService.getBookReviewList(book);
			List<HashMap<String, Object>> likes = bookService.getBookLikeList(book);		
			Integer ReCnt  = bookService.getReviewCnt(book);
			Integer LikeCnt = bookService.getLikeCnt(book);
			
			
			return ResponseEntity.status(200).body(BookDetailRes.of(book, reviews, likes, ReCnt, LikeCnt));
		} catch (Exception e) {
			// TODO: handle exception
			Book book = new Book();
			List<HashMap<String, Object>> reviews = new ArrayList<HashMap<String, Object>>();
			List<HashMap<String, Object>> likes = new ArrayList<HashMap<String, Object>>();
			return ResponseEntity.status(500).body(BookDetailRes.of(book, reviews, likes, 0, 0));
		}
	
	}
	
	
	@GetMapping("/list/genre")
	@ApiOperation(value = "장르별 도서 리스트 반환", notes = "도서목록 20개")
	@ApiResponses({
		@ApiResponse(code = 200, message = "성공"),
		@ApiResponse(code = 500, message = "실패")
	})
	public ResponseEntity <BookListRes> getBooksGenre (
			@RequestBody @ApiParam(value = "도서 리스트 정보 요청", required = true) BookListGetReq bookListInfo) {
		
		try {
			// genre 찾기
			Optional<Table_genre> genre = bookService.getGenre(bookListInfo.getType());
			genre.orElseThrow(() -> new Exception());
			
			// genre 관련한 것 찾아내기
			List<Book_genre> genreBookList = bookService.findGenreBook(genre.get());
			
			// Book List 받기 리뷰, 좋아요 카운트 포함
			List<HashMap<String, Object>> bookList = bookService.getGenBookList(genreBookList);
			
			return ResponseEntity.status(200).body(BookListRes.of(genre.get().getGenre(), bookList));
			
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(500).body(BookListRes.of("", new ArrayList<HashMap<String, Object>>()));
		}
	}
	
	@GetMapping("/list/topic")
	@ApiOperation(value = "주제어별 도서 리스트 반환")
	@ApiResponses({
		@ApiResponse(code = 200, message = "성공"),
		@ApiResponse(code = 500, message = "서버 에러")
	})
	public ResponseEntity<BookListRes> getBooksTopic(
			@RequestBody @ApiParam(value = "도서 리스트 종류", required = true) BookListGetReq bookListInfo) {
		
		try {
			Optional<Table_keyword> keyword = bookService.getKeyword(bookListInfo.getType());
			keyword.orElseThrow(() -> new Exception());
			
			// topic 에 관련된 book_keyword
			List<Book_keyword> keywordBookList = bookService.findKeywordBook(keyword.get()); // 이미 존재
			
			// Book List 받기, 리뷰 좋아요 카운트 포함
			List<HashMap<String, Object>> bookList = bookService.getKeywordBookList(keywordBookList);
			
			return ResponseEntity.status(200).body(BookListRes.of(keyword.get().getContent(), bookList));
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(500).body(BookListRes.of("", new ArrayList<HashMap<String, Object>>()));
		}
	}
	
	
	@PostMapping("/review/{bookId}")
	@ApiOperation(value = "도서 리뷰 작성하기", notes = "리뷰 점수와 리뷰 내용을 작성한다.")
	@ApiResponses({
		@ApiResponse(code = 201, message = "성공"),
		@ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<? extends BaseResponseBody> writeReview (
			@ApiIgnore Authentication authentication,
			@PathVariable("bookId") Long bookId,
			@RequestBody @ApiParam(value ="리뷰 정보", required = true) BookReviewPostReq bookReviewInfo) {
		try {
			// 검사
			SsafyUserDetails userDetails = (SsafyUserDetails) authentication.getDetails();
			String userId = userDetails.getUsername();
			User user = userService.getUserByUserId(userId);
			Book_review reviewInfo = bookService.saveReview(user, bookId, bookReviewInfo);			
			return ResponseEntity.status(201).body(BaseResponseBody.of(201, "Success"));	
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			return ResponseEntity.status(500).body(BaseResponseBody.of(500, "Failure"));
		}
	}
	
	@PostMapping("/like/{bookId}")
	@ApiOperation(value = "도서 좋아요", notes = "좋아요를 체크한다.")
	@ApiResponses({
		@ApiResponse(code = 201, message = "성공"),
		@ApiResponse(code = 500, message = "실패")
	})
	public ResponseEntity<? extends BaseResponseBody> likeBook (
			@ApiIgnore Authentication authentication,
			@PathVariable("bookId") Long bookId) {
		
		try {
			// 검사
			SsafyUserDetails userDetails = (SsafyUserDetails) authentication.getDetails();
			String userId = userDetails.getUsername();
			User user = userService.getUserByUserId(userId);
			
			// 좋아요가 있다면
			List<Book_like> existLike = bookService.getUserLike(user, bookId);
			if (existLike.isEmpty()) {
				Book_like userLike = bookService.saveLike(user, bookId);				
			}
			else {
				// 좋아요 삭제
				bookService.deleteLike(user, bookId);
			}			
			return ResponseEntity.status(200).body(BaseResponseBody.of(201, "Success"));
			
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(500).body(BaseResponseBody.of(500, "Failure"));
		}
	}
	
	@GetMapping("/planb/recommend")
	@ApiOperation(value = "추천 planB", notes = "추천시스템 플랜 B")
	@ApiResponses({
		@ApiResponse(code = 200, message = "성공"),
		@ApiResponse(code = 500, message = "실패")
	})
	public ResponseEntity<BookListRes> getRecommendSub (
			@ApiIgnore Authentication authentication) {
		
		try {
			// 검사
			SsafyUserDetails userDetails = (SsafyUserDetails) authentication.getDetails();
			String userId = userDetails.getUsername();
			User user = userService.getUserByUserId(userId);
			
			// 좋아요를 한 도서의 장르를 쭉 들고와서 랜덤으로 뽑는다? 각 장르마다 4개씩 5개의 장르			
			Set<Book> likeBooks = bookService.getUserLikeList(user);
			if (likeBooks.size() == 0) {
				throw new Exception("no like");
			}
			List<String> genreList = bookService.getBooktoGenre(likeBooks);
			List<Book> recomBookList = bookService.getRecommendBooks(genreList, likeBooks);
			List<HashMap<String, Object>> resRecomBookList = bookService.getBookResponse(recomBookList);
			
			return ResponseEntity.status(200).body(BookListRes.of("recommend", resRecomBookList));
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(200).body(BookListRes.of("null", new ArrayList<HashMap<String, Object>>()));
		}
	}
	
}
