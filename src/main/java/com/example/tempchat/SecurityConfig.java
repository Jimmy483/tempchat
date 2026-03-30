package com.example.tempchat;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

//@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

//    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
//
////        httpSecurity
////                .csrf(csrf->csrf.disable())
////                .authorizeHttpRequests(authorize -> authorize
////                .anyRequest().permitAll() // Allow all requests without authentication
////        );
//
////        httpSecurity
////                .authorizeHttpRequests(auth->auth
////                        .anyRequest()
////                        .permitAll())
////                .formLogin(form->form.disable());
//
//        httpSecurity
//                .csrf(AbstractHttpConfigurer::disable)
//                .authorizeHttpRequests(auth->auth
//                        .anyRequest().permitAll())
//                .formLogin(AbstractHttpConfigurer::disable)
//                .httpBasic(AbstractHttpConfigurer::disable)
//                .logout(AbstractHttpConfigurer::disable);
////        httpSecurity
////                .csrf(csrf->csrf
////                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
////                .authorizeHttpRequests(auth->auth
////                        .anyRequest().permitAll())
////                .formLogin(form->form.disable())
////                .logout(logout->logout.logoutUrl("/menu/logout").permitAll());
//        return httpSecurity.build();
//    }
}
