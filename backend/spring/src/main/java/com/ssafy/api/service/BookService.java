package com.ssafy.api.service;

import java.util.ArrayList;
import java.util.List;

import com.ssafy.api.request.BookReviewPostReq;
import com.ssafy.api.request.UserDuplicatedReq;
import com.ssafy.api.request.UserMbtiReq;
import com.ssafy.api.request.UserRegisterPostReq;
import com.ssafy.db.entity.Book_review;
import com.ssafy.db.entity.User;

/**
 *	유저 관련 비즈니스 로직 처리를 위한 서비스 인터페이스 정의.
 */
public interface BookService {
	Book_review saveReview(User user, Long bookId, BookReviewPostReq bookReviewInfo);
}
