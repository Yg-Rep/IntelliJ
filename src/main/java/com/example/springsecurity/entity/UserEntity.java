package com.example.springsecurity.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


//7 데이터 바구니를 만들어주자
@Entity
@Getter
@Setter
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    //ID가 자동으로생성되기위한 GeneratedValue 어노테이션


    //username 중복방지 위한 Column
    @Column(unique=true)
    private String username;
    private String password;
    private String role;


}
