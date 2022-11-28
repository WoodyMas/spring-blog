package com.codeup.springblog.controllers;

import com.codeup.springblog.models.User;
import com.codeup.springblog.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class UserController {
    private final UserRepository userDao;




    public UserController(UserRepository userDao) {
        this.userDao = userDao;
    }

//    public User user = new User("billBoB");

    @GetMapping("posts/allUsers")
    public String showAll(Model model){
        List<User> allUsers = userDao.findAll();
        model.addAttribute("allUsers", allUsers);
        return "/posts/users";
    }

    @GetMapping("/posts/users")
    public String showAllUsers(Model model){
        List<User> allUsers = userDao.findAll();
        model.addAttribute("allUsers", allUsers);
        return "posts/user-create";
    }

    @PostMapping("/posts/users")
    public String insertUser(@RequestParam (name = "username") String username, @RequestParam (name = "email") String email, @RequestParam (name = "password") String password, Model model){
        User newUser = new User(username, email, password);
        userDao.save(newUser);
        return "redirect:/posts/allUsers";
    }





//    @GetMapping("/posts/users")
//    public String showUsers(Model model){
//        List<User> userList = userDao.findAll();
//        model.addAttribute("allUsers", userList);
//        return "user-create";
//    }
//    @PostMapping("/posts/users")
//    public String insertUser (@RequestParam (name = "username") String username, @RequestParam (name = "email") String email, @RequestParam (name = "password") String password, Model model){
//        User user1 = new User(username, email, password);
//        userDao.save(user1);
//        return "redirect:/posts/users";
//    }

//    @GetMapping("/")
}
