package com.min.edu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.min.edu.bean01.FunctionExecution;
import com.min.edu.bean01.SamsungAircon;
import com.min.edu.bean01.SamsungTelevision;
import com.min.edu.inject.UseBean;

@SpringBootApplication
public class SpringBoot3BeanApplication implements CommandLineRunner {

	@Autowired
	private UseBean useBean;
	
	
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBoot3BeanApplication.class, args);
		
		// Container 생성
		ApplicationContext context = new AnnotationConfigApplicationContext(SamsungAircon.class);
		ApplicationContext context2 = new AnnotationConfigApplicationContext(SamsungTelevision.class);
		
		// bean 호출(@Bean, name)
		FunctionExecution airconOn = context.getBean("airconON", FunctionExecution.class);
		FunctionExecution airconOff = context.getBean("powerOff", FunctionExecution.class);
		
		// bean의 호출 (@Component)
		SamsungTelevision television = context2.getBean("samsungTelevision", SamsungTelevision.class);
		
		// bean의 실행
		System.out.println(airconOn.toString());
		System.out.println(airconOff.toString());
		System.out.println(television.powerOn());
		System.out.println(television.powerOff());
		
//		useBean.print();
	}


	// Spring Boot가 실행 되기 전 실행되어 값을 입력하거나, 선수 작업을 진행하는 CommandLineRunner
	@Override
	public void run(String... args) throws Exception {
		useBean.print();
		
	}

	
}
