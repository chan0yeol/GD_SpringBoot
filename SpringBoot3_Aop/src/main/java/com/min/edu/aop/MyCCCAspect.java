package com.min.edu.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyCCCAspect {
	
	@Pointcut("execution(public * com.min.edu.aop..*(..))")
	public void myClass() {
		
	}
	
	@Before("myClass()")
	public void beforeMethod() {
		System.out.println("메소드가 실행됩니다.");
	}
	
	@After("myClass()")
	public void afterMethod() {
		System.out.println("메소드가 종료됩니다.");
	}
}
