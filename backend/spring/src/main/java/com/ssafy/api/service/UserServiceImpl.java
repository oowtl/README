package com.ssafy.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ssafy.api.request.UserDuplicatedReq;
import com.ssafy.api.request.UserMbtiReq;
import com.ssafy.api.request.UserRegisterPostReq;
import com.ssafy.db.entity.Common;
import com.ssafy.db.entity.Common_detail;
import com.ssafy.db.entity.User;
import com.ssafy.db.repository.CommonDetailRepository;
import com.ssafy.db.repository.CommonRepository;
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
	CommonRepository commonRepository;
	
	@Autowired
	CommonDetailRepository commonDetailRepository;
	
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
		// 일반 정보 저장
		user.setNickname(userRegisterInfo.getNickname());
		user.setAge(userRegisterInfo.getAge());
		user.setSex(userRegisterInfo.getSex());
		user.setMbti(userRegisterInfo.getMbti());
		// tendency
		
		
		
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
	
	// 유저 ID 중복검사
	@Override
	public Boolean getUserIdDuplicated(UserDuplicatedReq userDuplicatedInfo) {
		// TODO Auto-generated method stub
		
		return userRepository.existsByUserId(userDuplicatedInfo.getContent());
	}
	// 유저 Nick 중복검사
	@Override
	public Boolean getUserNickDuplicated(UserDuplicatedReq userDuplicatedInfo) {
		// TODO Auto-generated method stub
		
		return userRepository.existsByNickname(userDuplicatedInfo.getContent());
	}
	
	// 유저 직업 반환
	@Override
	public List getUserJob() {
		// TODO Auto-generated method stub
		
		Common jobkey = commonRepository.findByName("job");
		List jobList = commonDetailRepository.findAllByCom(jobkey);
		
		return jobList;
	}
	
	@Override
	public User changeUserMbti(String userId, UserMbtiReq userMbtiReq) {
		// TODO Auto-generated method stub
		
		User user = userRepository.findByUserId(userId);
		user.setMbti(userMbtiReq.getResult());
		
		return userRepository.save(user);
	}
}
