package com.min.edu.filter;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
//@WebFilter(filterName = "loggerFilter", urlPatterns = {"*"})
@Component
public class LoggerFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		String url = StringUtils.defaultIfEmpty(req.getRequestURL().toString(), "-");
		String queryString = StringUtils.defaultIfEmpty(req.getQueryString(), "-");
		String remoteAddr = StringUtils.defaultIfEmpty(req.getRemoteAddr(), "-");
		
		String userAgent = StringUtils.defaultIfEmpty(req.getHeader("User-Agent"), "-");
		String refer = StringUtils.defaultIfEmpty(req.getHeader("Refer"),"-");
		
		String clientInfo = String.format("%s?%s : %s \n %s %s \n",url, queryString, remoteAddr, userAgent, refer);
		
		log.info("\n \n 클라이언트 정보 {} ",clientInfo);
		
		chain.doFilter(request, response);
	}

}
