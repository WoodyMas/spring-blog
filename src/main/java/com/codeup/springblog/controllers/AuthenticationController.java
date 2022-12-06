package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthenticationController {



    @GetMapping("/login")
    public String showLoginForm(){
        return "/login";
    }

    @PostMapping("/login")
    public String loginSubmitted(){
        return "/posts/show";
    }

    @GetMapping("/logout")
    public String logout(){
        return "redirect:/login?logout";
    }


}
