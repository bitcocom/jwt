package com.example.jwt.jwt;

public interface JwtProperties {
    public static final String SECRET_KEY = "cosin"; // 실제 사용 시에는 보다 복잡한 키를 사용하세요.
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final int EXPIRATION_TIME = 600000; // 10 minutes in milliseconds
}
