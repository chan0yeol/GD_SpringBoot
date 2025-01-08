package com.min.edu;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.min.edu.aop5.RightBrain;

@SpringBootTest
class Aop05_JUnitTest {

	@Autowired
	private RightBrain rb;
	
	@Test
	@DisplayName("AOP Test")
	void test() {
//		rb.thinking();
		rb.use("asd");
	}

}
