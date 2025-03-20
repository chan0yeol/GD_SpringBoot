package com.min.edu.service;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.min.edu.vo.User;

// TODO 003 User Entity CrudRepository 연결
@RepositoryRestResource(exported = false) // TODO 010 rest-data-api에서 user에 대한거 숨기기
public interface UserRepository extends CrudRepository<User, Long> {

	//  Security 인증 프로세서 에서 DB에서 user를 찾는데 사용되는 메소드
	// Optional로 작성하여 null 예외처리를 방지화기 위해서 사용
	Optional<User> findByUsername(String username); 
}