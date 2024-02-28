package com.example.jwt.service;

import com.example.jwt.entity.CustomUser;
import com.example.jwt.entity.PrincipalDetails;
import com.example.jwt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
// http://localhost:8081/login --> UserDetailsService 처리가 되는데 동작을 X
@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("PrincipalDetailsService_loadUserByUsername");
        CustomUser customUser=userRepository.findByUsername(username);
        return new PrincipalDetails(customUser);
    }
}
