package com.min.edu.ctrl;

import com.min.edu.service.JWTService;
import com.min.edu.vo.AccountCredentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

// 로그인 요청 처리를 위한 Controller
/*
    로그인 성공시 서명된 JWT를 생성하는데 필요한 JWT를 주입하여 사용한다.
 */
@RestController
public class LoginController {

    @Autowired
    private JWTService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<?> getToken(@RequestBody AccountCredentials credentials) {
        // 토큰을 생성하고 응답의 Authorization 헤더로 보냄
        UsernamePasswordAuthenticationToken creds = new UsernamePasswordAuthenticationToken(credentials.getUsername(), credentials.getPassword());
        Authentication auth =  authenticationManager.authenticate(creds);
        
        // 토큰 생성
        String jwts = jwtService.generateToken(auth.getName());

        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION,"Bearer "+jwts)
                .header(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS,"Authorization")
                .build();
    }

}
