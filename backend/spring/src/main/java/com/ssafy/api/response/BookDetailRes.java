package com.ssafy.api.response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.ssafy.db.entity.Book;
import com.ssafy.db.entity.Common_detail;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("BookDetailRes")
public class BookDetailRes {
	@ApiModelProperty(name = "도서 정보", example = "{id, title, author...}")
//	HashMap<String, Object> detail;
	Book detail;
	@ApiModelProperty(name = "리뷰 정보", example = "[{id, userId, score, content}]")
	List<HashMap<String, Object>> reviews;
	@ApiModelProperty(name = "좋아요 정보", example = "[{id, userId}]")
	List<HashMap<String, Object>> likes;
	@ApiModelProperty(name = "리뷰 숫자", example = "Integer")
	Integer review_cnt;
	@ApiModelProperty(name = "좋아요 숫자", example = "Integer")
	Integer like_cnt;
	
	public static BookDetailRes of(
			Book book,
			List<HashMap<String, Object>> reviews,
			List<HashMap<String, Object>> likes,
			Integer ReCnt,
			Integer LikeCnt
			) {
		
		BookDetailRes res = new BookDetailRes();
		res.setDetail(book);
		res.setReviews(reviews);
		res.setLikes(likes);
		res.setReview_cnt(ReCnt.intValue());
		res.setLike_cnt(LikeCnt.intValue());
		
		return res;
	}
	
	
}
