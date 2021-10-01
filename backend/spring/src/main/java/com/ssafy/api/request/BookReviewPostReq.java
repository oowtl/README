package com.ssafy.api.request;

import java.util.HashMap;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 도서 리뷰 등록을 위한 API POST book/review/{bookId}
 */
@Getter
@Setter
@ApiModel("BookReviewPostReq")
public class BookReviewPostReq {
	@ApiModelProperty(name="리뷰 점수", example="5")
	Integer score;
	@ApiModelProperty(name = "리뷰 내용", example="재미있어요!")
	String content;
	
	
}
