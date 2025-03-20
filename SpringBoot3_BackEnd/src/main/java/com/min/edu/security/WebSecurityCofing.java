package com.min.edu.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

// TODO 008 Security 인증을 설정하는 Config 클래스 
// Security 인증을 설정하는 Configuration 클래스
// : 2022년 2월 쯤에 WebSecurityConfigurerAdapter를 사용하는 것을 권장하지 않는다고 함.
//   컴포넌트 기반의 설정을 권장
@Configuration
@EnableWebSecurity
public class WebSecurityCofing {
	
	
	
	// 로그인 시도시 usernamePasswordAuthenticationToken을 통해서 
	// security에 전달하고 AuthenticationManager가 정보를 검증한다.
	// 인증을 성공하면 사용자 객체를 반환하고, 실패하면 예외(usernameNotFoundException)를 던진다.
	// AuthenticationManager는 인증을 수행하기 위해 UserDetailService를 사용하여 사용자 정보를 조회하고,
	// 입력된 비밀번호와 일치하는지 비교한다.
	// TODO 009 AuthenticationManager 작성
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
	
	// DetailService가 반환한 UserDatail 객체에서 저장된 암호화된 비밀번호와 사용자가 입력한 원본 비밀번호를 PasswordEncoder를 통해서 비교
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	
}
