package com.example.jwt.repository;

import com.example.jwt.entity.CustomUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<CustomUser, Long> {
    public CustomUser findByUsername(String username);
}
