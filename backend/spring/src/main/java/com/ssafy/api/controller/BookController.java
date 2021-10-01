package com.ssafy.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.api.request.BookReviewPostReq;
import com.ssafy.api.service.BookService;
import com.ssafy.api.service.UserService;
import com.ssafy.common.auth.SsafyUserDetails;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.Book_like;
import com.ssafy.db.entity.Book_review;
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
}
