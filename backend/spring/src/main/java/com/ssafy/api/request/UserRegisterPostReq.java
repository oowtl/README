package com.ssafy.api.request;

import java.util.HashMap;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 유저 회원가입 API ([POST] /api/v1/users) 요청에 필요한 리퀘스트 바디 정의.
 */
@Getter
@Setter
@ApiModel("UserRegisterPostRequest")
public class UserRegisterPostReq {
	@ApiModelProperty(name="유저 ID", example="ssafy_web")
	String userId;
	@ApiModelProperty(name="유저 Password", example="your_password")
	String password;
	@ApiModelProperty(name="유저 NickName", example="김싸피")
	String nickname;
	@ApiModelProperty(name="유저 age", example="25")
	Integer age;
	@ApiModelProperty(name="유저 sex", example="male")
	String sex;
	@ApiModelProperty(name="유저 MBTI", example="enfp")
	String mbti;
	@ApiModelProperty(name="유저 경향성", example="[{id: 1, check: 0}, {id:2, check: 1}, ...]")
	List<HashMap<String, Integer>> tendency;
}
