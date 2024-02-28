package com.example.jwt.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
public class FilterTest3 implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req= (HttpServletRequest) request;
        HttpServletResponse res= (HttpServletResponse) response;
        if(req.getMethod().equals("POST")) {
            System.out.println("POST 요청됨");
            String headerAuth = req.getHeader("Authorization");
            System.out.println(headerAuth);
            System.out.println("FilterTest3");
           // 토큰 이름이 cos이면
            if(headerAuth.equals("cos")){
                chain.doFilter(request, response); // 인증이 되면 --> Controller로 진입
            }else{
                PrintWriter out=res.getWriter();
                out.println("인증안됨");
            }
        }
    }
}
