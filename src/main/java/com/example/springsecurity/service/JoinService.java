package com.example.springsecurity.service;

import com.example.springsecurity.dto.JoinDTO;
import com.example.springsecurity.entity.UserEntity;
import com.example.springsecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JoinService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public void joinProcess(JoinDTO joinDTO){


        //db에 이미 동일한 username을 가진 회원이 존재하는지? 검증해서 있으면 return join; 없으면 밑의  login 로직 진행
    boolean isUser =userRepository.existsByUsername(joinDTO.getUsername());
    if(isUser){
        return; }


        //DTO를 엔티티로 변경시키는 작업을 해줘야 함.
        UserEntity data =new UserEntity();
        data.setUsername(joinDTO.getUsername());
        data.setPassword(bCryptPasswordEncoder.encode(joinDTO.getPassword()));
        data.setRole("ROLE_USER");
        // 존재하지않을시 entity 객체로 바꿔주고

    userRepository.save(data);

    }
}
