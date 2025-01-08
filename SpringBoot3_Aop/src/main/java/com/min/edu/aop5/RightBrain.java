package com.min.edu.aop5;

import org.springframework.stereotype.Component;

@Component
public class RightBrain implements IPerson {

	@Override
	public void thinking() {
		System.out.println("오른쪽 뇌가 동작하여 생각을 합니다.");
	}
	
	/*
	 * aop는 상위 부모에서 가지고 있는 메소드 만을 VMI로 실행을 시킨다.
	 * interface가 가지고 있지 않은 메소드는 작동의 대상이 되지 않는다.
	 * 
	 */
	public String use(String action) {
		System.out.println("반환과 argument가 있는 메소드 ");
		return action+"action";
	}
}
