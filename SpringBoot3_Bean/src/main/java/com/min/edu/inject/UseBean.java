package com.min.edu.inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.min.edu.bean01.SamsungTelevision;

@Component
public class UseBean {
	// 다른 Bean을 멤버필드 주입을 통해서 사용한다.
	@Autowired
	private SamsungTelevision samsungTelevision;
	
	public void print() {
		System.out.println("UseBean을 통한 외부 DI");
		System.out.println(samsungTelevision.powerOn());
		System.out.println(samsungTelevision.powerOff());
	}
}


















