package com.min.edu.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Aspect
@Slf4j
public class DaoLogAop_Anno {
	@Pointcut("execution(public * com.min.edu.model.mapper.*Dao*.*(..))")
	public void pointCut() {}
	
	@Before("pointCut()")
	public void before(JoinPoint j) {
		log.info("[AOP Lgger] Dao 메소드 실행 전");
		Object[] args = j.getArgs();
		if (args != null || args.length != 0) {
			log.info("[AOP LOGGER] : {}", j.getSignature().getName());
			for (int i = 0; i < args.length; i++) {
				log.info("\t{} 번째 : {}", i, args[i]);
			}
			log.info("[AOP LOGGER] : {}", j.getSignature().getName());
		}
	}
	
	@AfterReturning(value ="pointCut()", returning = "returnValue")
	public void afterReturning(JoinPoint j, Object returnValue) {
		log.info("[AOP LOGGER 반환 결과] : \t {}", returnValue);
	}
	@AfterThrowing(value ="pointCut()", throwing = "error")
	public void afterThrowing(JoinPoint j, Exception error) {
		log.info("[AOP LOGGER 오류] : \t {}", error);
	}
}
