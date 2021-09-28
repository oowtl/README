package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("UserMbtiReq")
public class UserMbtiReq {
	
	@ApiModelProperty(name = "유저 MBTI", example = "enfp")
	String result;
}
