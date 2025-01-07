package com.min.edu.anno04;

import org.springframework.stereotype.Component;

// @Component를 사용하여 자동으로 SpringBoot Application이 읽어서 Bean으로 등록해준다. 
@Component
public class Radio implements IFunction {

	@Override
	public void powerOn() {
		System.out.println("라디오 킴");
	}

	@Override
	public void powerOff() {
		System.out.println("라디오 끔");
	}

}
