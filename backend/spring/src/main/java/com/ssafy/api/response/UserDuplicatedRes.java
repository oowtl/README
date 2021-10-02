package com.ssafy.api.response;

import com.ssafy.common.model.response.BaseResponseBody;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

// 유저 닉네임, 아이디 중복체크 결과 (GET /api/v1/users/valDuplicated) 요청에 대한 응답값 정의

@Getter
@Setter
@ApiModel("UserDuplicatedRes")
public class UserDuplicatedRes extends BaseResponseBody {
	@ApiModelProperty(name = "결과", example="True")
	Boolean result;
	
	public static UserDuplicatedRes of(Boolean res) {
		UserDuplicatedRes r = new UserDuplicatedRes();
		r.setResult(res);
		return r;
	}	
}
