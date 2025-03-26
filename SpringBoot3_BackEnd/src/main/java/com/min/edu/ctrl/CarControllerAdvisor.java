package com.min.edu.ctrl;

import java.util.HashMap;import java.util.Map;

import org.springframework.http.HttpStatus;import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import lombok.extern.slf4j.Slf4j;
@ControllerAdvice
@Slf4j
public class CarControllerAdvisor {

	// 로그인 관련 404 예외 처리
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<Map<String, Object>> handleLoginNotFoundException(RuntimeException e) {
		log.error("로그인 정보를 찾을 수 없습니다. : {}", e);
		// 응답 객체를 직접 생성하여 JSON 형태로 변환
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("status", HttpStatus.NOT_FOUND.value()); // 404 코드
		response.put("msg", "로그인 정보를 찾을 수 없습니다. 사용자의 정보가 정확한지 확인해 주세요");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	}

	// NullPointException 처리(정보가 없을 경우)
	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<Map<String, Object>> handleNullPointExeption(NullPointerException e){
		log.error("정보가 없습니다. (NullPointException 발생) : {}", e);

// 응답 객체를 직접 생성하여 JSON 형태로 변환
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("status", HttpStatus.NOT_FOUND.value());
		response.put("msg", "정보를 찾을 수 없습니다");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	}

	@ExceptionHandler(NoResourceFoundException.class)
	public ResponseEntity<Map<String, Object>> handleNoResourceFoundException(NoResourceFoundException e){
		log.error("정적 리소스를 찾을 수 없습니다: {}", e);
// 응답 객체를 직접 생성하여 JSON 형태로 변환
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("status", HttpStatus.NOT_FOUND.value()); // 404 코드
		response.put("msg", "요청한 리소스를 찾을 수 없습니다. 요청한 경로를 확인해 주세요");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	}

	// 모든 예외를 처리하는 (Exception.class)
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Map<String, Object>> handleException(Exception e){
		log.error("오류가 발생 했습니다 : {}", e);
// 응답 객체를 직접 생성하여 JSON 형태로 변환
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value()); // 500 코드
		response.put("msg", "자동차 정보를 조회 하는 도중 오류가 발생 했습니다. 확인해 주세요");
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	}

}