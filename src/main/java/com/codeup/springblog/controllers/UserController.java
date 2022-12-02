package com.codeup.springblog.controllers;

import com.codeup.springblog.models.User;
import com.codeup.springblog.repositories.UserRepository;
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

    public UserController(UserRepository userDao, PasswordEncoder passwordEncoder){
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
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

    @GetMapping("/users/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "/user-registration";
    }

    @PostMapping("/users/register")
    public String registerUser(@ModelAttribute User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.save(user);
        return "redirect:/posts/create";
    }
}
