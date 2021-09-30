package com.ssafy.api.response;

import java.util.ArrayList;
import java.util.HashMap;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("UserTendencyRes")
public class UserTendencyRes {
	@ApiModelProperty(name = "contents", example = "{id: 1, title: ..., author, ...}")
	ArrayList<HashMap<String, Object>>contents;
	
	
	public static UserTendencyRes of(ArrayList<HashMap<String, Object>>contents) {
		
		
		
		return null;
	}
}
