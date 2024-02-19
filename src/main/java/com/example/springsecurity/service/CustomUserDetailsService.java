package com.example.springsecurity.service;

import com.example.springsecurity.dto.CustomUserDetails;
import com.example.springsecurity.entity.UserEntity;
import com.example.springsecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {


    // 실무에서는 생성자 주입방식으로 넣어야해 넣지말고
    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userData=userRepository.findByUsername(username);
        //UserRepository에서 지정해줘야지 위에 findByUsername 가능
if (userData!=null){

    return new CustomUserDetails(userData);
}

        return null;
    }
}
