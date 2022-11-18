package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Post;
import org.apache.logging.log4j.message.StringFormattedMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {

    @GetMapping("/posts")
//    @ResponseBody
    public String allPosts(Model model){

        Post postTest1 = new Post("I had a wonderful day! :)", "Today I had a wonderful day because I found a $20 bill!");
        Post postTest2 = new Post("Why do people take my money?", "I accidentally dropped a $20 bill, and when I went back to see where I left it, it was GONE!!!!!!!!!!!");
        List<Post> allPostList = new ArrayList<>(List.of(postTest2, postTest1));
        model.addAttribute("allPosts", allPostList);

        return "/posts/show";
    }

    @GetMapping("/posts/{id}")
//    @ResponseBody
    public String viewPostId(@PathVariable long id, Model model) {
        Post postView = new Post("MY Title", "This is the body of my post!It works THE MOST");
        model.addAttribute("aPost", postView);

        return "/posts/index";
    }

    @GetMapping("/posts/create")
    @ResponseBody
    public String viewPostForm(){
         return "<h1>spring-blog post form</h1>\n" +
                "    <form method=\"post\" action=\"/posts/create\">\n" +
                "        <label for=\"word\">Enter a word:</label>\n" +
                "        <input type=\"text\" name=\"word\" id=\"word\">\n" +
                "        <button type=\"submit\">Submit</button>\n" +
                "    </form>";
    }

    @PostMapping("/posts/create")
    @ResponseBody
    public String createPost(@RequestParam (name = "word") String word){

        return "<h1>spring-blog post form</h1>\n" +
                "    <form method=\"post\" action=\"/posts/create\">\n" +
                "        <div width=100%><label for=\"word\">Your word: " + word + "</label></div>\n" +
                "        Enter another word:\n" +
                "        <input type=\"text\" name=\"word\" id=\"word\">\n" +
                "        <button type=\"submit\">Submit</button>\n" +
                "<h1>Re-Compiler WORKS!!!!!! IT REALLY WORKS!!!!</h1>" +
                "    </form>";
//        return "You posted " + word + "!";
    }



} // End PostController Class
