package com.min.edu.aop;

import org.springframework.stereotype.Component;

@Component
public class Man implements IHuman {

	@Override
	public void classWork() {
		System.out.println("Man의 클래스의 주 기능을 실행합니다.");
	}
	
}
