package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("UserDuplicatedReq")
public class UserDuplicatedReq {

	@ApiModelProperty(name = "중복체크 Type", example = "id")
	String val;
	@ApiModelProperty(name = "중복체크 content", example = "user Id")
	String content;
}
