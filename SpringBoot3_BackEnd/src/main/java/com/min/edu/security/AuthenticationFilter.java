package com.min.edu.security;


import com.min.edu.service.JWTService;
import com.min.edu.service.UserDetailServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

// TODO [JWT] doFilterInternal 를 Override하여 JWTService를 사용하여 필터처리 한다.

@Component
public class AuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JWTService jwtService;

    @Autowired
    private ApplicationContext applicationContext;

    /*
        Filter의 흐름 처리
        1. JWT 인증 성공 :
            JWT 토큰이 유효하고, 사용자 정보 올바르며, 인증객체가 SecurityContextHolder에 설정 된다.
            Spring Security 는 인증된 사용자로 요청을 계속 처리한다.
            필터 및 컨트롤러 요청이 전달

        2. JWT 인증 실패 :
            토큰이 유효하지 않거나 사용자 정보가 잘못된 경우, 인증객체가 설정되지 않는다.
            요청은 인증되지 않은 상태로 계속 진행하는 권한이 부족(401 Unauthorized 또는 403 Forbidden) 응답으로 처리
     */


    // 요청필터당 한번씩 호출하여 필터를 확장화 하기 위해서 OncePerRequestFilter를 extends 하여 구현한다.
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 1) Client 부터 요청된 토큰을 받는다.
        String authToken = request.getHeader("Authorization");
        String token = null;
        String username = null;

        // 2) JWT 를 클라이언트 요청의 헤더에서 추출
        if (authToken != null && authToken.startsWith("Bearer ")) {
            token = authToken.substring(7);
            // 3) token으로 부터 JWTService에 사용자의 이름을 추출한다.
            username = jwtService.extractUserName(token);
        }


        // 4) JWT에서 사용자 이름 추출하고 && 현재 인증정보가 없으면 사용자 정보를 조회한다.
        //    추출된 username을 통해서 UserDetailService를 통해 사용자 정보인 UserDetails 객체를 조회한다.
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = applicationContext.getBean(UserDetailServiceImpl.class)
                    .loadUserByUsername(username);

            // 5) JWT가 유효한지 판단하여 유효하다면 인증객체(UsernamePasswordAuthenticationToken)를 생성하고 Spring Security Context에 저장

            // 토큰 검증 (토큰 만료일, 토큰이 해당 사용자에 대한 것이 맞는지)
            if (jwtService.validationToken(token, userDetails)) {
                // 사용자 권한 확인 정보, 정보가 원하지 않는 자격증명은 null, 원하는 권한 전달한다.
                UsernamePasswordAuthenticationToken auth =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }

        filterChain.doFilter(request, response);
    }
}
