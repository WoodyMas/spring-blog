package com.codeup.springblog.controllers;

import com.codeup.springblog.models.User;
import com.codeup.springblog.repositories.UserRepository;
import com.codeup.springblog.services.EmailService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class UserController {
    private final UserRepository userDao;

    private final PasswordEncoder passwordEncoder;

    private final EmailService emailService;

    public UserController(UserRepository userDao, PasswordEncoder passwordEncoder, EmailService emailService){
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
    }

    public UserRepository getUserDao() {
        return userDao;
    }

    public PasswordEncoder getPasswordEncoder() {
        return passwordEncoder;
    }


    @GetMapping("/allUsers")
    public String showAll(Model model){
        List<User> allUsers = userDao.findAll();
        model.addAttribute("allUsers", allUsers);
        return "/posts/users";
    }

    @GetMapping("/users")
    public String showAllUsers(Model model){
        List<User> allUsers = userDao.findAll();
        model.addAttribute("allUsers", allUsers);
        return "posts/user-create";
    }

    @PostMapping("/users")
    public String insertUser(@RequestParam (name = "username") String username, @RequestParam (name = "email") String email, @RequestParam (name = "password") String password, Model model){
        User newUser = new User(username, email, password);
        userDao.save(newUser);
        return "redirect:/allUsers";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "/user-registration";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        if (user.getPassword() != "" && user.getEmail() != "" && user.getPassword() != "") {
            userDao.save(user);
            // We loves the String.format() >:) how very useful >:)))))
            emailService.prepareAndSend(user, String.format("A new user: %s has registered!", user.getUsername()), String.format("""
                Username: %s
                Email: %s
                """, user.getUsername(), user.getEmail()));
            return "redirect:/posts/create";

        }
        else {
            return "redirect:/register";
        }

    }
}
