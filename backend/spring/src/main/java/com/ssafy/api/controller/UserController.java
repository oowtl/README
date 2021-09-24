package com.ssafy.api.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.api.request.UserDuplicatedReq;
import com.ssafy.api.request.UserLoginPostReq;
import com.ssafy.api.request.UserRegisterPostReq;
import com.ssafy.api.response.UserDuplicatedRes;
import com.ssafy.api.response.UserLoginPostRes;
import com.ssafy.api.response.UserRes;
import com.ssafy.api.service.UserService;
import com.ssafy.common.auth.SsafyUserDetails;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.common.util.JwtTokenUtil;
import com.ssafy.db.entity.User;
import com.ssafy.db.repository.UserRepositorySupport;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 유저 관련 API 요청 처리를 위한 컨트롤러 정의.
 */
@Api(value = "유저 API", tags = {"User"})
@RestController
@RequestMapping("/api/v1/users")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@PostMapping("/create")
	@ApiOperation(value = "회원 가입", notes = "<strong>아이디와 패스워드</strong>를 통해 회원가입 한다.") 
    @ApiResponses({
        @ApiResponse(code = 200, message = "성공"),
        @ApiResponse(code = 401, message = "인증 실패"),
        @ApiResponse(code = 404, message = "사용자 없음"),
        @ApiResponse(code = 500, message = "서버 오류")
    })
	public ResponseEntity<? extends BaseResponseBody> register(
			@RequestBody @ApiParam(value="회원가입 정보", required = true) UserRegisterPostReq registerInfo) {
		
		//임의로 리턴된 User 인스턴스. 현재 코드는 회원 가입 성공 여부만 판단하기 때문에 굳이 Insert 된 유저 정보를 응답하지 않음.
		
		if(userService.getUserByUserId(registerInfo.getUserId())==null) {
			System.out.println("중복되는 아이디가 없습니다. SUCCESS!");
			User user = userService.createUser(registerInfo);
			return ResponseEntity.status(201).body(BaseResponseBody.of(201, "Success"));
		} else {
			System.out.println("중복되는 아이디가 있습니다. FAIL!");
			return ResponseEntity.status(500).body(BaseResponseBody.of(500, "Failure"));
		}
	}
	
	@GetMapping("/me")
	@ApiOperation(value = "회원 본인 정보 조회", notes = "로그인한 회원 본인의 정보를 응답한다.") 
    @ApiResponses({
        @ApiResponse(code = 200, message = "성공"),
        @ApiResponse(code = 401, message = "인증 실패"),
        @ApiResponse(code = 404, message = "사용자 없음"),
        @ApiResponse(code = 500, message = "서버 오류")
    })
	public ResponseEntity<UserRes> getUserInfo(@ApiIgnore Authentication authentication) {
		/**
		 * 요청 헤더 액세스 토큰이 포함된 경우에만 실행되는 인증 처리이후, 리턴되는 인증 정보 객체(authentication) 통해서 요청한 유저 식별.
		 * 액세스 토큰이 없이 요청하는 경우, 403 에러({"error": "Forbidden", "message": "Access Denied"}) 발생.
		 */
		SsafyUserDetails userDetails = (SsafyUserDetails)authentication.getDetails();
		// 아이디를 불러옴
		String userId = userDetails.getUsername();
		User user = userService.getUserByUserId(userId);
		
		return ResponseEntity.status(200).body(UserRes.of(user));
	}
	
	@GetMapping("/valDuplicated")
	@ApiOperation(value = "아이디, 닉네임 중복체크", notes = "회원가입 시 중복체크 진행")
	@ApiResponses({
		@ApiResponse(code = 200, message = "검사 성공"),
		@ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<UserDuplicatedRes> duplicateUser (
			@RequestBody UserDuplicatedReq userduplicated) {
		
		// 1차 검사 val 이 잘 들어왔는가?
		try {
			
			Exception er = new Exception();

			// val 검사
			String valType = userduplicated.getVal();
			String valContent = userduplicated.getContent();
			
			// 내용 없음
			if ("".equals(valType) || "".equals(valContent) ) {
				throw er;
			}
			
			switch (valType) {
			case "id":
				System.out.println("id");
				String userIdValRes = userService.getUserIdDuplicated(userduplicated);
				return ResponseEntity.status(200).body(UserDuplicatedRes.of(userIdValRes));

			case "nickname":
				String userNickValRes = userService.getUserNickDuplicated(userduplicated);
				return ResponseEntity.status(200).body(UserDuplicatedRes.of(userNickValRes));
				
			default:
				throw er;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(200).body(UserDuplicatedRes.of("Error"));
		} 
	}
	
//	@GetMapping("/modify")
//	@ApiOperation(value = "회원 본인 정보 수정", notes = "로그인한 회원 본인의 정보를 수정한다.") 
//	@ApiResponses({
//		@ApiResponse(code = 200, message = "성공"),
//		@ApiResponse(code = 401, message = "인증 실패"),
//		@ApiResponse(code = 404, message = "사용자 없음"),
//		@ApiResponse(code = 500, message = "서버 오류")
//	})
//	public ResponseEntity<BaseResponseBody> modifyUserInfo(@ApiIgnore Authentication authentication, @RequestBody @ApiParam(value="회원가입 정보", required = true) UserRegisterPostReq registerInfo) {
//		SsafyUserDetails userDetails = (SsafyUserDetails)authentication.getDetails();
//		String userId = userDetails.getUsername();
//		System.out.println("come in");
//		userService.modifyUser(userId, registerInfo);
//		
////		return ResponseEntity.status(200).body(UserRes.of(user));
//		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
//	}
}
