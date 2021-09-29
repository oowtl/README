package com.ssafy.api.response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.ssafy.db.entity.Common_detail;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("UserCreateJobRes")
public class UserCreateJobRes {
	@ApiModelProperty(name = "결과", example = "{소방관, 경찰관...}")
	List content;
	
	public static UserCreateJobRes of(List<Common_detail> content) {
		
		List cRes = new ArrayList<String>();
		
		for (Common_detail con : content) {
			cRes.add(con.getCommon_detail());
		}
		
		UserCreateJobRes res = new UserCreateJobRes();
		res.setContent(cRes);
		return res;
	}
	
}
