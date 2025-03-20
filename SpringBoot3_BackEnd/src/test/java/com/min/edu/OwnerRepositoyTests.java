package com.min.edu;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.CALLS_REAL_METHODS;

import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.min.edu.service.OwnerRepository;
import com.min.edu.vo.Car;
import com.min.edu.vo.Owner;

import jakarta.transaction.Transactional;

@SpringBootTest
class OwnerRepositoyTests {

	@Autowired
	private OwnerRepository ownerRepository;
	
	
	/*
	 * 자식 테이블(car)는 @ManyToOne으로 부모(owner)와 fetch=FetchType.EAGER로 되어 있지만
	 * 부모 테이블(owner)는 @OneToMany 으로 자식(car)과 fetch=FetchType.LAZY로 되어 있다
	 * 따라서 Lazy Loading(지연로딩)으로 인하여 호출시 쿼리를 실행하기 때문에 초기화 되어 있지 않기 때문에 오류를 발생시킨다 - no session
	 * 1) @Transcational을 통해서 해당 메소드에서 Transaction을 처리 할 수 있도록
	 * 2) JOIN FETCH를 통해 연관된 Entity들을 즉시로딩(Eager Loading) 방식으로 가져온다.
	 * 	  JOIN FETCH 는 @Query(JPQL)에서 사용하는 방법으로 변경
	 * 3) @EntityGraph를 통해서 명시적으로 즉시 로딩할 연관 속성을 지정
	 *    findAll()과 같은 JPA 기본 메소드에 @EntityGraph를 적용하여 즉시로딩 되도록 한다.
	 */
	
//	@Transactional // getCars() : car 필드를 접근할때 세션이 열려 있어 지연로딩이 가능하도록 한다.
	@Test
	public void ownerRepository_Test() { 
//		List<Owner> find = ownerRepository.findAll();
		// 2) JOIN FETCH 를 통해서 연관된 Entity를 즉시로딩 방식으로 가져온다.
//		List<Owner> find = ownerRepository.findAllWithCars();
		// 3) @EntityGraph : 명시적으로 즉시 로딩할 연관 속성을 지정
		List<Owner> find = ownerRepository.findAll();
			
		for(Owner o : find) {
			System.out.println(o.getFirstname().concat("/").concat(o.getLastname()));
			System.out.println(o.getCars());
		}
		assertNotNull(find);
				
	}
}