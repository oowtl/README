package com.ssafy.api.response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("UserTendencyRes")
public class UserTendencyRes {
	@ApiModelProperty(name = "contents", example = "{id: 1, title: ..., author, ...}")
	List<Object> contents;
	
	
	public static UserTendencyRes of(List<Object> contents) {
		UserTendencyRes	res = new UserTendencyRes();
		res.setContents(contents);
		return res;
	}
}
