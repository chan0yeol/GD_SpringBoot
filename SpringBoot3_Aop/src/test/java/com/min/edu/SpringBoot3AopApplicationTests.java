package com.min.edu;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.min.edu.aop.Man;
import com.min.edu.aop.Wowan;

@SpringBootTest
class SpringBoot3AopApplicationTests {

	@Autowired
	private Man man;
	
	@Autowired
	private Wowan wowan;
	
	@Test
	@DisplayName("AOP 테스트")
	void contextLoads() {
		man.classWork();
		wowan.classWork();
	}

}
