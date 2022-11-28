package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Post;
import com.codeup.springblog.models.User;
import com.codeup.springblog.repositories.PostRepository;
import com.codeup.springblog.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {
    private final PostRepository postDao;
    private final UserRepository userDao;


    @GetMapping("/posts")
//    @ResponseBody
    public String allPosts(Model model){

//        Post postTest1 = new Post(postDao, "I had a wonderful day! :)", "Today I had a wonderful day because I found a $20 bill!");
//        Post postTest2 = new Post(postDao, "Why do people take my money?", "I accidentally dropped a $20 bill, and when I went back to see where I left it, it was GONE!!!!!!!!!!!");
//        List<Post> allPostList = new ArrayList<>(List.of(postTest2, postTest1));
//        model.addAttribute("allPosts", allPostList);
        List<Post> allPosts = postDao.findAll();
        model.addAttribute("posts", allPosts);


        return "/posts/show";
    }

    @GetMapping("/posts/{id}")
//    @ResponseBody
    public String viewPostId(@PathVariable long id, Model model) {
        Post postView = postDao.findById(id);
        model.addAttribute("aPost", postView);

        return "/posts/index";
    }

    @GetMapping("/posts/create")
//    @ResponseBody
    public String viewPostCreateForm(){
         return "/posts/create";
    }

    @PostMapping("/posts/create")
    public String createNewController(@RequestParam(name="titleInput") String title, @RequestParam(name="bodyInput") String body ){
        User user = userDao.findUserById(1);
        Post post = new Post(user, title, body);
        postDao.save(post);
        return "redirect:/posts";
    }

//    @PostMapping("/posts/create")
//    @ResponseBody
//    public String createPost(@RequestParam (name = "word") String word){
//
//        return "<h1>spring-blog post form</h1>\n" +
//                "    <form method=\"post\" action=\"/posts/create\">\n" +
//                "        <div width=100%><label for=\"word\">Your word: " + word + "</label></div>\n" +
//                "        Enter another word:\n" +
//                "        <input type=\"text\" name=\"word\" id=\"word\">\n" +
//                "        <button type=\"submit\">Submit</button>\n" +
//                "<h1>Re-Compiler WORKS!!!!!! IT REALLY WORKS!!!!</h1>" +
//                "    </form>";
////        return "You posted " + word + "!";
//    }

///////////////////////// Constructor ////////////////////////////////
    public PostController(PostRepository postDao, UserRepository userDao){

        this.postDao = postDao;
        this.userDao = userDao;


    }

//    @PostMapping
//    public String joinPostUser(@RequestParam (name = "title") String title, @RequestParam (name = "body") String body, @RequestParam (name = "email") String email, @RequestParam (name = "username") String username){
//
//        return "";
//    }


} // End PostController Class
