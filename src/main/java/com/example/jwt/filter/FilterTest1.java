package com.example.jwt.filter;

import jakarta.servlet.*;

import java.io.IOException;

public class FilterTest1 implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("FilterTest1");
        chain.doFilter(request, response);
    }
}
