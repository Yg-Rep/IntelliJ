package com.example.springsecurity.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;



//Data 관련해서 data를 어노테이션 해주면 Controller에서 가져다가 쓸수있음 Board board 매개변수로 받아서 board.getId() 이것을 사용가능하게 해주는거.
@Data
@Entity
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    //primary key 의미  Id
    //identity같은경우는 mysql에서 사용하는거다
    //jpa가 entity를 읽어서 자동으로 처리해줌.

    private Integer id;
    private String title;
    private String content;

    //여기서 위의 변수들은 table에 맞게 지정해줘야함

}
