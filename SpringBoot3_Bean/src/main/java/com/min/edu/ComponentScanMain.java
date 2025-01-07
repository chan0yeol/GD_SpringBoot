package com.min.edu;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

import com.min.etc.Television;

//@ComponentScan(basePackages = "com.min.etc")
@ComponentScan({"com.min.etc", "com.min.e"})
public class ComponentScanMain {

	public static void main(String[] args) {
		ApplicationContext bean = new AnnotationConfigApplicationContext(ComponentScanMain.class);
		
		Television tv = bean.getBean("television", Television.class);
		tv.powerOn();
		tv.powerOff();
	}	

}
