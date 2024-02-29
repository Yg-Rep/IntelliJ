package com.example.springsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration       //config 파일 등록
@EnableWebSecurity   //스프링 시큐리티 등록
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){

        return new BCryptPasswordEncoder();
    }
    // 스프링은 모든 비밀번호에 암호화를 진행해야함
    //스프링 시큐리티는 사용자 인증시 비밀번호에 대한 단방향 해시 암호화를 진행하여 db에 저장되어있는 비밀번호와 대조
    // 스프링 시큐리티는 BCrypt Password Encoder를 제공함. Bean으로 등록
    // BcryptPasswordEncoder가 자동으로 생성자로 등록될수 있게 만들어둠.

    @Bean
    public RoleHierarchy roleHierarchy() {

        RoleHierarchyImpl hierarchy = new RoleHierarchyImpl();

        hierarchy.setHierarchy("ROLE_C > ROLE_B\n" +
                "ROLE_B > ROLE_A");

        return hierarchy;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        //람다식으로 작성해야함 시큐리티 6.0이상부턴
        //.requestMatchers().permitAll()
        // ** 두개는 와일드카드
        // 상단부터 순서가 적용되기에 허용을 잘해줘야한다.
        // 가장아래에서 모든경로 허용 및 접근제한해야함.

      /*  http
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/", "/login","loginProc","/join","/joinProc").permitAll()
                        .requestMatchers("/admin").hasRole("ADMIN")
                        .requestMatchers("/my/**").hasAnyRole("ADMIN", "USER")
                        .anyRequest().authenticated()
                );*/

        // 3강 인가 및 허용
        // my 페이지에 대해서 my/유저들을 위해 ** 와일드카드 적용 여러개의 주소를 위해 와일드카드
        // login에 대해 모든사람에게 인가
        // main 또한 모든 사람에게 인가
        // admin은 admin role을 가지고있는 사람에게만 적용
        // anyRequest authenticated()-> 로그인한 사용자만 접근할수 있도록 적용


        //인가동작순서 상단부터 순서대로 임으로 잘생각해서 인증 인가를 해야함.
        http
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/login","loginProc","join","joinProc").permitAll()
                        .requestMatchers("/").permitAll()
                        .requestMatchers("/admin").hasAnyRole("ADMIN")
                        .requestMatchers("/my/**").hasAnyRole("ADMIN","USER")
                        .anyRequest().authenticated()
                );



        //login 페이지 설정 해주면 스프링시큐리티가 자동으로 로그인페이지로 리다이렉팅해줌
        //html 로그인 폼 태그에서 post로 받아온 loginProc를 받아서 시큐리티가 로그인처리를 진행

        //loginProceesingUrl("/loginProc")



        http
                /*.httpBasic(Customizer.withDefaults());*/
        // http basic 인증방식 가장기본적으로 http에서 로그인방식을 진행

        //form Login을통한 인증방식
        //login pg가 어디있는지 변수로 설정해줌.
                //login page가 있으면 자동으로 security가 인증인가를 거쳐 없으면 로그인페이지로 보내줌.

                .formLogin((auth) -> auth.loginPage("/login")
                        .loginProcessingUrl("/loginProc")
                        .permitAll()
                );


        // http
        //        .csrf((auth) -> auth.disable());

        //csrf disable을 없애면 자동으로 방어를 위해 로그인이 막힘.
        //고로 csrf 토큰관련해서 적어줘야함.




        //csrf는 사이트위변조 방지설정 스프링시큐리티에는 자동으로 설정이되어있음.
        // post요청을 보낼때 토큰도 csrf 토큰을 보내야지 받을수 있다.
        // 고로 개발환경에서는 auth.disable를 통해 잠깐 멈춰두자!


        //최대 .maximumSession 하나의 아이디에서 동시접속 중복허용 개수를 설정해줄수있고,
        // maxSessionsPreventsLogin을통해 막아줄수있다.
        // 다중로그인 개수를 초과할경우 true일시 새로운 로그인 차단
        // false일시 기존 로그인을 로그아웃하고 새로운 아이디 로그인해줌
        http
                .sessionManagement((auth) -> auth
                        .maximumSessions(1)
                        .maxSessionsPreventsLogin(true));


        http
                .sessionManagement((auth)->auth
                        .sessionFixation().changeSessionId());
        //세션 아이디 보호 chageSessionId();
        //세션 고정 보호 설정 관련


        http
                .logout((auth) -> auth.logoutUrl("/logout")
                        .logoutSuccessUrl("/"));
        //Get 방식 로그아웃 진행 설정 방법


        return http.build();
    }
}
