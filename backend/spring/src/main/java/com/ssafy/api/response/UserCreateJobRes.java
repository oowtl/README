package com.ssafy.api.response;

import java.util.ArrayList;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("UserCreateJobRes")
public class UserCreateJobRes {
	@ApiModelProperty(name = "결과", example = "{소방관, 경찰관...}")
	ArrayList<String> content;
	
	public static UserCreateJobRes of(ArrayList<String> content) {
		UserCreateJobRes res = new UserCreateJobRes();
		res.setContent(content);
		return res;
	}
	
}
