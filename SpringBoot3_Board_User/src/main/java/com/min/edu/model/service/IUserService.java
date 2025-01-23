package com.min.edu.model.service;

import java.util.List;
import java.util.Map;

import com.min.edu.vo.UserVo;

public interface IUserService {
	// 로그인
	UserVo getLogin(Map<String, Object> map);

	// 중복검사
	int isDuplicateCheck(String id);

	// 회원가입
	int signupMember(UserVo vo);

	// 회원전체조회
	List<UserVo> userSelectAll();

	// 회원전체조회
	List<UserVo> getAllUser();

	// 회원검색
	List<UserVo> getSearcherUser(Map<String, Object> map);

	// 아이디찾기
	String findId(Map<String, Object> map);

	// 권한 변경
	int setChangeAuth(Map<String, Object> map);
//		int setChangeAuth(List<String> list);  
}
