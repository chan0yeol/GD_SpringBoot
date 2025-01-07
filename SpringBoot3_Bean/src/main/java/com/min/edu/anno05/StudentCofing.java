package com.min.edu.anno05;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// Spring Bean Configuration 에 xml 작성을 통해서 Bean을 등록하여 여러개를 사용했던 클래스
@Configuration
public class StudentCofing {
	
	@Bean(name="stu01")
	public Student student01() {
		return new Student("홍길동", "탐라국", "999");
	}
	
	@Bean(name="stu02")
	public Student student02() {
		return new Student("또치", "성북", "48");
	}
	
	@Bean(name="stu03")
	public Student student03() {
		return new Student("ㅁㅇㅇ", "#@$@#", "!@$SDF");
	}
	
	@Bean(name="school")
	public School school01() {
		return new School(null, 1); 
	}
}
