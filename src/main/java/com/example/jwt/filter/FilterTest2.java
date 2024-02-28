package com.example.jwt.filter;

import jakarta.servlet.*;

import java.io.IOException;

public class FilterTest2 implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("FilterTest2");
        chain.doFilter(request, response);
    }
}
