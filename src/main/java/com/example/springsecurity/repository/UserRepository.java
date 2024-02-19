package com.example.springsecurity.repository;

import com.example.springsecurity.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity,Integer> {

    boolean existsByUsername(String username);
    //join할때 username이 있나 없나 확인해서 존재하면 true 없으면 false 값을보냄

    UserEntity findByUsername(String username);
    //jpa 커스텀메소드를 작성가능하게해주는거
}
