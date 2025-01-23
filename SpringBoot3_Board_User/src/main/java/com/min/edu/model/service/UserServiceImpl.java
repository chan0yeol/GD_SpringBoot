package com.min.edu.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.min.edu.model.mapper.IUserDao;
import com.min.edu.vo.UserVo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements IUserService{
	private final IUserDao dao;

	@Override
	public UserVo getLogin(Map<String, Object> map) {
		log.info("UserServiceImpl getLogin");
		return dao.getLogin(map);
	}

	@Override
	public int isDuplicateCheck(String id) {
		log.info("UserServiceImpl isDuplicateCheck ");
		return dao.isDuplicateCheck(id);
	}

	@Override
	public int signupMember(UserVo vo) {
		log.info("UserServiceImpl signupMember ");
		return dao.signupMember(vo);
	}

	@Override
	public List<UserVo> userSelectAll() {
		log.info("UserServiceImpl userSelectAll");
		return dao.userSelectAll();
	}

	@Override
	public List<UserVo> getAllUser() {
		log.info("UserServiceImpl getAllUser");
		return dao.getAllUser();
	}

	@Override
	public List<UserVo> getSearcherUser(Map<String, Object> map) {
		log.info("UserServiceImpl getSearcherUser");
		return dao.getSearcherUser(map);
	}

	@Override
	public String findId(Map<String, Object> map) {
		log.info("UserServiceImpl findId");
		return dao.findId(map);
	}

	@Override
	public int setChangeAuth(Map<String, Object> map) {
		log.info("UserServiceImpl setChangeAuth");
		return dao.setChangeAuth(map);
	}
}
