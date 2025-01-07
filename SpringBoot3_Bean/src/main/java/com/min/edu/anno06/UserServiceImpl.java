package com.min.edu.anno06;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor // 생성자 주입
public class UserServiceImpl implements IUserService {
	
//	@Autowired 
//	private UserDto dto01; // 멤버필드 주입
	
//	@Autowired
//	private UserDto dto01;
//	@Qualifier("dto01")
//	public void setDto01(UserDto dto01) { // setter 주입
//		this.dto01 = dto01;
//	}
	
	private UserDto dto01;
	
	@Override
	public void addUser() {
		log.info("추가된 멤버 : {} ", dto01.getName());
	}
}
