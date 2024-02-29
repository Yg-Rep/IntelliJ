package com.example.springsecurity.dto;

import com.example.springsecurity.entity.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class CustomUserDetails implements UserDetails {
    //UserDetails implements
    //임플리먼트 메소드를 통해 구현해두자.
    //db기반 로그인 검증로직

    private UserEntity userEntity;

    public CustomUserDetails(UserEntity userEntity){
        this.userEntity=userEntity;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Collection<GrantedAuthority> collection = new ArrayList<>();
        //Collection 클래스 선언해주고 GrantedAuthority라고 내부타입 지정을해주고 collection 객체를 생성한다.
        collection.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {

                return userEntity.getRole();
            }
        });
        return collection;
    }

    @Override
    public String getPassword() {
        return userEntity.getPassword();
    }

    @Override
    public String getUsername() {
        return userEntity.getUsername();
    }


    //강제로 true로 로직을 해둠.

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
