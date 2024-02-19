package com.example.springsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        //Bcrypt 모든비밀번호 암호화
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        //람다식으로 작성해야함 시큐리티 6.0이상부턴
        //.requestMatchers().permitAll()
        // ** 두개는 와일드카드
        // 상단부터 순서가 적용되기에 허용을 잘해줘야한다.
        // 가장아래에서 모든경로 허용 및 접근제한해야함.

        http
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/", "/login").permitAll()
                        .requestMatchers("/admin").hasRole("ADMIN")
                        .requestMatchers("/my/**").hasAnyRole("ADMIN", "USER")
                        .anyRequest().authenticated()
                );


        //login 페이지 설정 해주면 스프링시큐리티가 자동으로 로그인페이지로 리다이렉팅해줌
        //html 로그인 폼 태그에서 post로 받아온 loginProc를 받아서 시큐리티가 로그인처리를 진행
        http
                .formLogin((auth) -> auth.loginPage("/login")
                        .loginProcessingUrl("/loginProc")
                        .permitAll()
                );
        http
                .csrf((auth) -> auth.disable());

        //csrf는 사이트위변조 방지설정 스프링시큐리티에는 자동으로 설정이되어있음.
        // post요청을 보낼때 토큰도 csrf 토큰을 보내야지 받을수 있다.
        // 고로 개발환경에서는 auth.disable를 통해 잠깐 멈춰두자!

        return http.build();
    }
}
