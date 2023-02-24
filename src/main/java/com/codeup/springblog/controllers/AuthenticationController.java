package com.codeup.springblog.controllers;

import com.codeup.springblog.models.User;
import com.codeup.springblog.repositories.UserRepository;
import com.codeup.springblog.services.EmailService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthenticationController {

    public EmailService emailService;
    public UserRepository userDao;


    @GetMapping("/login")
    public String showLoginForm(){
        return "/login";
    }

    @PostMapping("/login")
    public String loginSubmitted(@RequestParam (name = "username") String username, @RequestParam (name = "username-search") String usernameSearch){
        if (usernameSearch != null) {


            if (userDao.findByUsername(usernameSearch) != null) {
                User requestingUser = userDao.findByUsername(usernameSearch);
                System.out.println(requestingUser.getUsername());
                emailService.prepareAndSend(requestingUser, String.format("Hi %s! You forgot your password!!", requestingUser.getUsername()), "Reset your password here: ");
                return "/posts/splash";
            }


        }

        return "/posts/show";
    }

    @GetMapping("/logout")
    public String logout(){
        return "redirect:/login?logout";
    }


    public static void main(String[] args) {

        System.out.println();
    }
}
