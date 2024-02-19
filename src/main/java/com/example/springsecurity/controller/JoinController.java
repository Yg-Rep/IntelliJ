package com.example.springsecurity.controller;

import com.example.springsecurity.dto.JoinDTO;
import com.example.springsecurity.service.JoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class JoinController {


    // 생성자주입방식으로 해줘야해 반드시
    @Autowired
    private JoinService joinService;


@GetMapping("/join")
    public String joinP(){
    return "join";
}

@PostMapping("/joinProc")
    public String joinProcess(JoinDTO joinDTO){

    System.out.println(joinDTO.getUsername());
    joinService.joinProcess(joinDTO);

    return "redirect:/login";
}

}
