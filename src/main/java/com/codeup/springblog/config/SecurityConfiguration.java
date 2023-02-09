package com.codeup.springblog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {
    @Bean
    // everything behind permitAll() will be viewed by visitors and users alike
    // everything before .authenticated will be viewable by only authenticated users
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
                .antMatchers("/", "/posts", "/posts/{id}(id=${post.id})", "/register").permitAll()
                .antMatchers("/posts/create", "/posts/{id}/edit", "/posts/", "/my-tools", "/allUsers").authenticated()
                // in the /login page, if successfully logged in, user will be redirected to splash page
                .and().formLogin().loginPage("/login").defaultSuccessUrl("/splash")
                .and().logout()
                .and().httpBasic();
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
