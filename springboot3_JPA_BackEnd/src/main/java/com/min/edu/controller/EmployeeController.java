package com.min.edu.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.min.edu.exception.ResourceNotFoundException;
import com.min.edu.model.Employee;
import com.min.edu.repository.IEmployeeRepository;

import io.micrometer.common.util.StringUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Slf4j
public class EmployeeController {

	private final IEmployeeRepository employeeRepository;

	// 직원 전체 조회
	@GetMapping("/employees")
	public List<Employee> findAll() {
		return employeeRepository.findAll();
	}

	// 직원 입력 하기
	@PostMapping("/employees")
	public Employee createEmployee(@RequestBody Employee employee) {
		return employeeRepository.save(employee);
	}
	
	// 직원 id로 조회하기
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeID(@PathVariable Long id){
		// orElseThorw() : 찾는 값이 없다면 예외를 발생시켜 준다.
		// get() : JPA에서 검색된 값은 객체가 아니기 때문에 해당 타입을 캐스팅하여 값을 추출
		Employee employee = employeeRepository.findById(id)
							.orElseThrow(()-> new ResourceNotFoundException(id+"사원은 존재하지 않습니다."));
		return ResponseEntity.ok(employee);
	}
	
	@PatchMapping("/employees/{id}")
	@Transactional
	public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee inEmployee){
		Employee employee = employeeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException(id+"사원은 존재하지 않습니다."));
		// 화면에서 전달 받은 값을 검색색된 Employee 객체에 영속적으로 값을 변경하면 자동으로 DB의 값이 변경된다.
		if(StringUtils.isNotBlank(employee.getFirstName())) {
			employee.setFirstName(inEmployee.getFirstName());
		}
		if(StringUtils.isNotBlank(employee.getLastName())) {
			employee.setLastName(inEmployee.getLastName());
		}
		if(StringUtils.isNotBlank(employee.getEmailId())) {
			employee.setEmailId(inEmployee.getEmailId());		
		}
		
		return ResponseEntity.ok(employee);
	}
	
	// 삭제
	@DeleteMapping("/employees/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id) {
		Employee employee = employeeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException(id+"사원이 존재하지 않음"));
		employeeRepository.delete(employee);
		Map<String, Boolean> resp = new HashMap<String, Boolean>();
		// 삭제 됬을 경우 메세지 전달
		resp.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(resp);
	}

}
