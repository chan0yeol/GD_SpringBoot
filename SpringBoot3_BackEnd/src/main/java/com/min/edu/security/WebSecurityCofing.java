package com.min.edu.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// TODO 008 Security 인증을 설정하는 Config 클래스 
// Security 인증을 설정하는 Configuration 클래스
// : 2022년 2월 쯤에 WebSecurityConfigurerAdapter를 사용하는 것을 권장하지 않는다고 함.
//   컴포넌트 기반의 설정을 권장
@Configuration
@EnableWebSecurity
public class WebSecurityCofing {

	@Autowired
	private AuthenticationFilter authenticationFilter;

//	403 security 처리 클래스
	@Autowired
	private AuthEntryPoint authEntryPoint;

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
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// TODO SecurityFilterChain을 통해서 POST요청의 /login은 인증 없어도 되고, Security 가 세션을 생성하지 않도록 정의하여 csrf 비활성화
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http
				.csrf(csrf -> csrf.disable()) // csrf를 비활성화
				.authorizeHttpRequests(request ->  
						request.requestMatchers("/login").permitAll() // Login Controller의 /login 요청은 인증없이 요청 허용
						.anyRequest().authenticated() // 그 외의 요청은 모두 요청인증을 필요로 해야함
				)
				// 인증방식은 계정과 비밀번호를 base64로 인코딩하여 서버로 전달하여 기본방식을 사용한다.
//				.httpBasic(Customizer.withDefaults())
				.httpBasic(httpBasic -> httpBasic.authenticationEntryPoint(authEntryPoint))
				// 서버가 사용자의 세션을 생성하거나 저장하지 않음, JWT와 같은 Token기반 인증을 사용하는
				// application에서 사용, 클라이언트는 매 요청마다 인증 정보를 포함
				.sessionManagement(session  -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class)
				.build();
	}





	
	
}
