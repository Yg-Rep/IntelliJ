package com.example.springsecurity.service;

import com.example.springsecurity.entity.Board;
import com.example.springsecurity.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardService {

    //Dependency injection
    @Autowired
    private BoardRepository boardRepository;
    public void write(Board board){

        boardRepository.save(board);
    }
}
