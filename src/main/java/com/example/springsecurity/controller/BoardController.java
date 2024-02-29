package com.example.springsecurity.controller;

import com.example.springsecurity.entity.Board;
import com.example.springsecurity.service.BoardService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BoardController {

    @Autowired
private BoardService boardService;
    //어떤 URL로 접근할것인지 이경우 localhost/templates/board
    @GetMapping("/write")
    public String boardWriteForm(){

        return "board";
    }

    @PostMapping("/writeProc")
    public String boardWritePro(Board board){
                            //매개변수엔 String title,String content 이게들어가지만 귀찮아지니까


        System.out.println(board.getContent());
        boardService.write(board);
        return "";
    }
}
