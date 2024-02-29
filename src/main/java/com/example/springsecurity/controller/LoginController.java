package com.example.springsecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
@GetMapping("/login")
    public String loginP(){
    //위는 /login 요청에서 오면
    // 밑 login.mustache와 동일하게

    return "login";
}
}
