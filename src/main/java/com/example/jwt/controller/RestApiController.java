package com.example.jwt.controller;

import com.example.jwt.entity.CustomUser;
import com.example.jwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
public class RestApiController {

      @Autowired
      private UserRepository userRepository;
      @Autowired
     private BCryptPasswordEncoder passwordEncoder;
       @GetMapping("/home")
       public String home(){
           return "<h1>home</h1>";
       }

    @PostMapping("/token")
    public String token(){
        return "<h1>token</h1>";
    }
    @PostMapping("/join")
    public String join(@RequestBody CustomUser user){
          user.setPassword(passwordEncoder.encode(user.getPassword()));
          user.setRoles("ROLE_USER");
          userRepository.save(user);
          return "회원가입";
    }
    @GetMapping("/api/v1/user")
    public String user(){
           return "user";
    }
    @GetMapping("/api/v1/manager")
    public String manager(){
        return "manager";
    }
    @GetMapping("/api/v1/admin")
    public String admin(){
        return "admin";
    }
}
