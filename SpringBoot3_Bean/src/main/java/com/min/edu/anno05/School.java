package com.min.edu.anno05;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class School {
	
//	@Autowired // 어떠한 Bean도 생성되어 있지 않다면 오류 발생
//	@Autowired(required = false) // 생성된 Bean이 없을경우 오류가 아닌 null 입력되도록 해줌
	
//	@Autowired
//	@Qualifier("stu01") // 여러개의 Bean이 있는 경우 Autowired는 타입기반이기 때문에 여러개가
//						//존재하여 오류 발생 특정한 이름의 Bean을 호출하는
	
//	@Resource(name="stu01") // Resource는 이름 기반으로 Bean을 주입 . 
							// SpringBoot3 에서는 jakarta.annotation.api를 사용 
	
//	@Resource
//	@Qualifier("stu01")
	private Student student;
	
	private int grade;
	
	
}
