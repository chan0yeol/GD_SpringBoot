package com.min.edu.sample;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.min.edu.model.Employee;
import com.min.edu.repository.IEmployeeRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SampleData implements CommandLineRunner {

	private final IEmployeeRepository employeeRepository;
	
	@Override
	public void run(String... args) throws Exception {
//		Employee employee1 = new Employee("홍길동", "김", "hong@gil.com");
//		Employee employee2 = new Employee("김영희", "박", "kim@young.com");
//		Employee employee3 = new Employee("이철수", "이", "lee@c.com");
//		
//		employeeRepository.saveAll(List.of(employee1,employee2,employee3));
	}

}
