package com.alus.security.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //접근권한
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

//        http.authorizeRequests((auth)->auth
//                .requestMatchers("/","/login","/loginProc","/join","/joinProc", "//*.css", "/**/*.js", "/**/*.png", "/**/*.jpg", "/**/*.jpeg", "/**/*.gif", "/favicon.ico").permitAll()
//                .requestMatchers("/admin").hasRole("ADMIN")
//                .requestMatchers("/my/**").hasAnyRole("ADMIN","USER")
        http.authorizeRequests()
                .requestMatchers("/static/**").permitAll()
                .requestMatchers("/**").permitAll(); // 이 줄을 임시로 추가하여 모든 요청을 허용하도록 합니다.


        http.formLogin((auth)->auth.loginPage("/login")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/indexLogin", true)
                .usernameParameter("email")
                .permitAll()
        );

        http.csrf((auth)->auth.disable());
        return http.build();
    }
}