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
@ApiModel("BookListRes")
public class BookListRes {
	@ApiModelProperty(name = "장르 타입", example= "한국소설, 로맨스소설")
	String type;
	@ApiModelProperty(name = "도서 정보 20개", example = "{id, title...}, {}")
	List<HashMap<String, Object>> contents;
	
	public static BookListRes of (
			String type,
			List<HashMap<String, Object>> bookList) {
		
		BookListRes res = new BookListRes();
		res.setType(type);
		res.setContents(bookList);
		
		return res;
	}
	
}
