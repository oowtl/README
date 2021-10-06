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
@ApiModel("BookListGetReq")
public class BookListGetReq {
	@ApiModelProperty(name="도서 리스트 정보요청", example="한국소설")
	String type;
	
	
}
