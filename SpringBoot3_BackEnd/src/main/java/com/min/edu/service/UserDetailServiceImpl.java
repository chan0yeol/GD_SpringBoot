package com.min.edu.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.min.edu.vo.User;

import lombok.Builder;

// TODO 006 Security인증 UserDetails의 구현체 작성
// UserDetailService 사용자의 인증과 인가에 대한 정보를 담고 있는 security 객체
@Service
public class UserDetailServiceImpl implements UserDetailsService{

	
	@Autowired
	private UserRepository userRepository; // TODO 007 사용자를 DB에서 조회할 수 있는 JPA repository 연결
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// 1) 전달 받은 username 을 통한 사용자 조회 
		Optional<User> user= userRepository.findByUsername(username);
		
		// 2) Security 에서 사용자를 판단하기 위한 UserBuilder 객체를 생성
		UserBuilder userBuilder = null;
		
		if(user.isPresent()) {
			// 3) 사용자가 있다면 UserDetails의 User 타입으로 변환
			User currentUser = user.get();
			
			// 4) withUsername() 메소드는 username을 입력 받아 반환 값으로 UserBuilder 객체를 반환한다.   
			//	  -> UserBuilder 객체 내부에 있는 username에 withUsername(String param) 의 매개변수로 들어온 값을 할당.
			userBuilder = org.springframework.security.core.userdetails.User.withUsername(username);
			
			// 5) DB에서 검색된 password를 할당
			userBuilder.password(currentUser.getPassword());
			
			// 6) DB에서 검색된 role을 할당
			userBuilder.roles(currentUser.getRole());
			
		} else {
			throw new UsernameNotFoundException("사용자가 없습니다.");
		}
		
		return userBuilder.build();
	}
	
	
	

}
