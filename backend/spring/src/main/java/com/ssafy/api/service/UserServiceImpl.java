package com.ssafy.api.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ssafy.api.request.UserDuplicatedReq;
import com.ssafy.api.request.UserMbtiReq;
import com.ssafy.api.request.UserRegisterPostReq;
import com.ssafy.db.entity.Book;
import com.ssafy.db.entity.Book_author;
import com.ssafy.db.entity.Book_genre;
import com.ssafy.db.entity.Book_like;
import com.ssafy.db.entity.Book_tendency;
import com.ssafy.db.entity.Common;
import com.ssafy.db.entity.Common_detail;
import com.ssafy.db.entity.Table_author;
import com.ssafy.db.entity.Table_genre;
import com.ssafy.db.entity.User;
import com.ssafy.db.repository.BookAuthorRepository;
import com.ssafy.db.repository.BookGenreRepository;
import com.ssafy.db.repository.BookLikeRepository;
import com.ssafy.db.repository.BookRepository;
import com.ssafy.db.repository.BookTendencyRepository;
import com.ssafy.db.repository.CommonDetailRepository;
import com.ssafy.db.repository.CommonRepository;
import com.ssafy.db.repository.TableAuthorRepository;
import com.ssafy.db.repository.TableGenreRepository;
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
	BookRepository bookRepository;
	
	@Autowired
	BookGenreRepository bookGenreRepository;
	
	@Autowired
	BookAuthorRepository bookAuthorRepository;
	
	@Autowired
	BookLikeRepository bookLikeRepository;
	
	@Autowired
	BookTendencyRepository bookTendencyRepository;
	
	@Autowired
	TableGenreRepository tableGenreRepository;
	
	@Autowired
	TableAuthorRepository tableAuthorRepository;
	
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
		
		User createUser = userRepository.save(user); 
		// tendency
		
		List<HashMap<String, Integer>> tenList = userRegisterInfo.getTendency(); 
		for (int i = 0; i < tenList.size(); i++) {
			if (tenList.get(i).get("check") == 0) {
				
				Book chBook = bookRepository.findOneById(tenList.get(i).get("id").longValue()).get();
				
				// 좋아요
				Book_like blike = new Book_like();
				blike.setBook(chBook);
				blike.setUser(createUser);
				bookLikeRepository.save(blike);
				
				// tendency
				Book_tendency bten = new Book_tendency();
				bten.setBook(chBook);
				bten.setUser(createUser);
				bookTendencyRepository.save(bten);
				
			}
		}
		// jpaRepository의 save가 데이터가 새로 추가되면 insert를 실행
		return createUser;
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
	public ArrayList<User> getUserIdDuplicated(String content) {
		// TODO Auto-generated method stub
		
		return userRepository.findAllByUserId(content);
	}
	// 유저 Nick 중복검사
	@Override
	public ArrayList<User> getUserNickDuplicated(String content) {
		// TODO Auto-generated method stub
		
		return userRepository.findAllByNickname(content);
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
	
	@Override
	public List getTendencyBooks() {
		// TODO Auto-generated method stub
		
		List<Object> tendencyBooks = new ArrayList<Object>();
		
		// 장르 키워드 (임의 선정)
		// 4 한국 소설, 6 로맨스 소설, 54 과학, 88 철학, 94 미술, 105 취미/실용/스포츠, 121 종교일반, 133 역사 문화, 143 성공/처세, 250 경제일반
		Long choiceGenre[] = new Long[] {4L, 6L, 54L, 88L, 94L, 105L, 121L, 133L, 143L, 250L};
		
		Random ran = new Random();
		
		for (int i = 0; i < choiceGenre.length; i++) {
			
			HashMap<String, Object> bookInfo = new HashMap();
			Table_genre genreTable = tableGenreRepository.findById(choiceGenre[i]).get();
			ArrayList<Book_genre> bgList = (ArrayList<Book_genre>) bookGenreRepository.findFirst10ByGenre(genreTable);
			int listChoice = ran.nextInt(bgList.size());
			Book tbook = bookRepository.findOneById(bgList.get(listChoice).getBook().getId()).get();
			Book_author bookAuthor = bookAuthorRepository.findOneByBook(tbook);
			// id, title, author, story, img
			bookInfo.put("id", tbook.getId());
			bookInfo.put("title", tbook.getTitle());
			bookInfo.put("author", bookAuthor.getAuthor_aid().getAuthor());
			bookInfo.put("story", tbook.getStory());
			bookInfo.put("img", tbook.getImg());			
			tendencyBooks.add(bookInfo);
		}
		
		return tendencyBooks;
	}
}
