package com.min.edu.service;



import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

// TODO [JWT] JWT를 생성하고 검증하는 클래스 Bean
@Service
public class JWTService {

    private String secretKey;
    // TODO [JWT] 생성자를 통해서 고유한 HmaSHA256알고리즘을 사용하여 키를 생성함
    public JWTService() {
        try {
            KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
            SecretKey sk = keyGen.generateKey();
            secretKey = Base64.getEncoder().encodeToString(sk.getEncoded());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
    // 생성된 Key를 통해서 사용자 정보를 암호화한 key를 secretKey를 통해서 키를 만들어 냄.
    private SecretKey getKey() {
        byte[] keyByte = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyByte);
    }

    public String generateToken(String username) {
        // 이름, 만료일 등을 통한 정보 입력, Map으로 생성후에 Jwts.claims를 통해서 변환
        // 0.12 이전버전 setClaim을 통해서 생성
        Map<String, Object> claims = new HashMap<>();
        return Jwts.builder()
                .claims() // claim은 JWT 본문에 들어 가는 데이터(사용자 정보 등)
                .add(claims)
                .subject(username) // 인증된 사용자의 username을 대상으로 함.
                .issuedAt(new Date(System.currentTimeMillis())) // JWT가 발급된 시간
                .expiration(new Date(System.currentTimeMillis()+(60*1000*10))) // JWT의 만료시간
                .and()
                .signWith(getKey()) // 서명을 생성하는데 사용되는 키
                .compact(); // JWT를 직렬화 하여 문자열 형태로 변환 즉, 최종 JWT 토큰 문자열
    }

    // JWT 인증 및 Security 로그인 정보를 확인하는 AuthenticationFilter 에서 사용하는 메소드
    // 전달된 Header 정보에서 token을 추출하는 기능
    public String extractUserName(String token) {
        // jwt token에서 다양한 claims 필드에서 사용자 이름을 추출
        return extractClaim(token, Claims::getSubject);
    }

    // java8에서 도입된 함수형 인터페이스
    // Function<입력값 t, 출력값 R> 형변환
    // Claims 객체를 받아서 T 타입의 값을 반환하는 함수
    private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims); // 함수형 인터페이스 apply를 통해서 처리 결과를 반환 
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getKey()) // JWT 서명을 검증, 서명시 사용한 키를 제공하는 메소드, 서명 유효성 확인
                .build()// paser 객체를 빌드
                .parseSignedClaims(token) // JWT 토큰을 파싱, 서명이 유효한 경우 Claim 객체를 반환
                .getPayload(); // Calims 페이로드 부분(데이터)를 추출
    }

    // 사용자 인증상태 && 토큰 만료 확인
    public boolean validationToken(String token, UserDetails userDetails) {
        final String username = extractUserName(token);

        return (username.equals(userDetails.getUsername()) && !isTokenExpried(token));
    }

    // JWT에서 토큰이 만료되었는지 확인하는 메소드
    private boolean isTokenExpried(String token) {
        return extractExpriration(token).before(new Date());
    }

    // JWT 에서 만료시간을 추출하는 메소드
    private Date extractExpriration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
}

