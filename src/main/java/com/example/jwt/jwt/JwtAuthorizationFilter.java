package com.example.jwt.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.jwt.entity.CustomUser;
import com.example.jwt.entity.PrincipalDetails;
import com.example.jwt.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.io.IOException;

// 시큐리티가 filter를 가지고 있는데 그 필터중에 BasicAuthenticationFilter라는 것이 있음
// 권한이나 인증이 필요한 특정 주소를 요청했을 때 위 필터를 무조건 타게 되어있음
// 권한이나 인증처리시 무조건 이 필터를 타게 되어있음
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {
    private UserRepository userRepository;
    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, UserRepository userRepository) {
        super(authenticationManager);
        this.userRepository = userRepository;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader(JwtProperties.HEADER_STRING);

        if (header == null || !header.startsWith(JwtProperties.TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }

        String token = header.replace(JwtProperties.TOKEN_PREFIX, "");
        try {
            String username = JWT.require(Algorithm.HMAC256(JwtProperties.SECRET_KEY.getBytes()))
                    .build()
                    .verify(token)
                    .getClaim("username")
                    .asString();

            if (username != null) {
                CustomUser customUser = userRepository.findByUsername(username);
                PrincipalDetails principalDetails = new PrincipalDetails(customUser);
                Authentication authentication = new UsernamePasswordAuthenticationToken(principalDetails, null, principalDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception e) {
           //e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401 상태 코드 설정
            response.setContentType("application/json"); // 응답 컨텐트 타입 설정
            response.setCharacterEncoding("UTF-8"); // 문자 인코딩 설정

            // JSON 형식의 오류 메시지 생성
            String errorMessage = "{\"error\": \"Unauthorized\", \"message\": \"Access Denied\"}";
            response.getWriter().write(errorMessage);
        }
        chain.doFilter(request, response);
    }
}
