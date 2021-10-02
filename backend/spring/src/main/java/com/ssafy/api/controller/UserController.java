package com.ssafy.api.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.api.request.UserDuplicatedReq;
import com.ssafy.api.request.UserLoginPostReq;
import com.ssafy.api.request.UserMbtiReq;
import com.ssafy.api.request.UserRegisterPostReq;
import com.ssafy.api.response.UserCreateJobRes;
import com.ssafy.api.response.UserDuplicatedRes;
import com.ssafy.api.response.UserLoginPostRes;
import com.ssafy.api.response.UserRes;
import com.ssafy.api.response.UserTendencyRes;
import com.ssafy.api.service.UserService;
import com.ssafy.common.auth.SsafyUserDetails;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.common.util.JwtTokenUtil;
import com.ssafy.db.entity.User;
import com.ssafy.db.repository.UserRepository;
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
@RequestMapping("/users")
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
	
	@GetMapping("/valDuplicated/{val}/{content}")
	@ApiOperation(value = "아이디, 닉네임 중복체크", notes = "회원가입 시 중복체크 진행")
	@ApiResponses({
		@ApiResponse(code = 200, message = "검사 성공"),
		@ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<UserDuplicatedRes> duplicateUser (
			@PathVariable("val") String val,
			@PathVariable("content") String content) {
		
		System.out.println(val + content);
		
		// 1차 검사 val 이 잘 들어왔는가?
		try {		
			Exception er = new Exception();
			// val 검사
			String valType = val;
			String valContent = content;
			
			// 내용 없음
			if ("".equals(valType) || "".equals(valContent) ) {
				throw er;
			}
			
			switch (valType) {
			case "id":
				ArrayList<User> userIdValRes = userService.getUserIdDuplicated(content);
				if (userIdValRes.isEmpty()) {
					return ResponseEntity.status(200).body(UserDuplicatedRes.of(true));
				}
				else {
					return ResponseEntity.status(200).body(UserDuplicatedRes.of(false));
				}
			case "nick":
				ArrayList<User> userNickValRes = userService.getUserNickDuplicated(content);
				
				if (userNickValRes.isEmpty()) {					
					return ResponseEntity.status(200).body(UserDuplicatedRes.of(true));
				}
				else {
					return ResponseEntity.status(200).body(UserDuplicatedRes.of(false));
				}
			default:
				throw er;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(200).body(UserDuplicatedRes.of(false));
		} 
	}
	
	// 회원 가입 시 리스트 반환
	@GetMapping("create/job")
	@ApiOperation( value = "회원가입 시 직업 리스트 반환", notes = "직업 리스트 반환")
	@ApiResponses({
		@ApiResponse(code = 200, message = "리스트 반환 성공"),
		@ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<UserCreateJobRes> userJob () {
		
		try {
			List createJobList = userService.getUserJob();
			return ResponseEntity.status(200).body(UserCreateJobRes.of(createJobList));
		} catch (Exception e) {
			// TODO: handle exception
			List er = new ArrayList();
			return ResponseEntity.status(500).body(UserCreateJobRes.of(er));
		}
	}
	
	
	// user mbti 저장하기
	@PostMapping("profile/mbti")
	@ApiOperation( value = "유저 mbti 수정 및 등록", notes = "mbti 수정 및 등록")
	@ApiResponses({
		@ApiResponse(code = 200, message = " 수정 및 등록 성공"),
		@ApiResponse(code = 500, message = " 서버 오류")
	})
	public ResponseEntity<? extends BaseResponseBody> userMbti (
			@ApiIgnore Authentication authentication,
			@RequestBody UserMbtiReq uMbti
			) {
		
		// 로그인 검사
		try {
			SsafyUserDetails userDetails = (SsafyUserDetails) authentication.getDetails();
			String userId = userDetails.getUsername();
			// mbti 추
			User user = userService.changeUserMbti(userId, uMbti);
			
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(500).body(BaseResponseBody.of(400, "Fail"));
		}
		
		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
	}
	
	// 유저 tendency 검사를 위한 책 리스트 반환하기
	@GetMapping("profile/tendency")
	@ApiOperation(value = "유저 tendency 검사를 위한 책 리스트 반환하기", notes ="10권 정도 선택할 수 있도록 한다.")
	@ApiResponses({
		@ApiResponse(code = 200, message = "반환 성공"),
		@ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<UserTendencyRes> userTendencyTest () {
		
		try {
			List ten10Books = userService.getTendencyBooks();
			return ResponseEntity.status(200).body(UserTendencyRes.of(ten10Books));
		} catch (Exception e) {
			// TODO: handle exception
//			System.out.println(e);
			List<Object> erRes = new ArrayList<Object>();
			return ResponseEntity.status(400).body(UserTendencyRes.of(erRes));
		}
	}
	
}
