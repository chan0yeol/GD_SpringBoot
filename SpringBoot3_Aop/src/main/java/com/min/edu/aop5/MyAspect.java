package com.min.edu.aop5;

import java.util.Iterator;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MyAspect {
	@Pointcut("execution(public * com.min.edu.aop5..*(..))")
	public void usePointCutBrain() {}
	
	@Before("usePointCutBrain()")
	public void beforeMethod() {
		System.out.println("오른쪽 생각 시작 Before");
	}
	
	@After("usePointCutBrain()")
	public void afterMethod() {
		System.out.println("오른쪽 생각 종료 After ");
	}
	
	@Around("usePointCutBrain()")
	public Object around(ProceedingJoinPoint joinPoint) {
		System.out.println("메소드 시작전 공통으로 시작됨 - Around");
		String methodName = joinPoint.getSignature().getName();
		System.out.println(methodName + "실행 - Around");
		
		try {
			Object res = joinPoint.proceed();
			System.out.println(methodName + " 실행결과" + res + " - Around");
			return res;
		} catch (Throwable e) {
			System.out.println("예외처리되었습니다.");
			e.printStackTrace();
			return null;
		}
	}
	
	@AfterReturning(value = "usePointCutBrain()", returning = "returnValue")
	public void afterReturning(JoinPoint joinPoint, Object returnValue) {
		System.out.println("(┬┬﹏┬┬):-D:-)" + joinPoint.getSignature().getName()); 
		
		Object[] args = joinPoint.getArgs();
		System.out.println("리턴 되는 값 : " + returnValue);
		for(Object o : args) {
			System.out.println("메소드를 실행하기 위한 argument" + o.toString());
		}
	}
}
