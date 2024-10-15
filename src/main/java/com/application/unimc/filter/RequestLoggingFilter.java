package com.application.unimc.filter; // 패키지 이름은 자유롭게 설정

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.Date;

@Component
public class RequestLoggingFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        // 요청 로그 남기기
        System.out.println("============== Request Info ==============");
        System.out.println("IP Address: " + request.getRemoteAddr());
        System.out.println("HTTP Method: " + httpRequest.getMethod());
        System.out.println("Request URI: " + httpRequest.getRequestURI());
        System.out.println("Time : " + new Date());
        System.out.println("==========================================");
        
        // 헤더 로그 기록
        httpRequest.getHeaderNames().asIterator().forEachRemaining(headerName ->
            System.out.println(headerName + ": " + httpRequest.getHeader(headerName))
        );

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {}
}
