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
    //회원가입 실패시 회원가입 성공시 따로 지정도 해줘야함.
//joinservice로 넘겨준다.
    return "redirect:/login";
    }


    //순서 join.mustache에서 폼태그로 JoinProc로 보내면
    // joinController에서 joinProc를 Post매핑으로 받은뒤
    // joinDTO를 거쳐
    // joinService로 넘겨주고 joinservice에서는 repository에 있는 데이터를 존재하는 여부인지 bool로 확인하고
    // 존재하지않으면 dto객체를 entity객체로 바꿔줌

    //config에서 /join , /joinProc에 모든 허용을 인가해주면  join관련 끝. 

}
