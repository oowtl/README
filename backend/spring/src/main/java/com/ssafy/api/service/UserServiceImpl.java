package com.ssafy.api.service;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ssafy.api.request.UserDuplicatedReq;
import com.ssafy.api.request.UserRegisterPostReq;
import com.ssafy.db.entity.User;
import com.ssafy.db.repository.UserRepository;
import com.ssafy.db.repository.UserRepositorySupport;

/**
 *	유저 관련 비즈니스 로직 처리를 위한 서비스 구현 정의.
 */
@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserRepositorySupport userRepositorySupport;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Override
	public User createUser(UserRegisterPostReq userRegisterInfo) {
		User user = new User();
		System.out.println(userRegisterInfo.getUserId());
		user.setUserId(userRegisterInfo.getUserId());
		// 보안을 위해서 유저 패스워드 암호화 하여 디비에 저장.
		user.setPassword(passwordEncoder.encode(userRegisterInfo.getPassword()));
		
		user.setNickname(userRegisterInfo.getNickname());
		user.setAge(userRegisterInfo.getAge());
		user.setSex(userRegisterInfo.getSex());
		
		// jpaRepository의 save가 데이터가 새로 추가되면 insert를 실행
		return userRepository.save(user);
	}

	@Override
	public User getUserByUserId(String userId) {
		// 디비에 유저 정보 조회 (userId 를 통한 조회)
		if(userRepositorySupport.findUserByUserId(userId).isPresent()) {
			User user = userRepositorySupport.findUserByUserId(userId).get();
			return user;
		} else return null;
	}

//	@Override
//	public void modifyUser(String userId, UserRegisterPostReq userRegisterInfo) {
//		if(userRepositorySupport.findUserByUserId(userId).isPresent()) {
//			System.out.println("아이디가 존재");
//			User user = userRepositorySupport.findUserByUserId(userId).get();
//			userRepositorySupport.updateUserByUserId(userId, userRegisterInfo);
//		}
//		
//	}
	
	// 중복검사하기
	@Override
	public String getUserDuplicted(UserDuplicatedReq userDuplicatedInfo) {
		// TODO Auto-generated method stub
		
		
		
		return "!";
	}
}
