package com.codeup.springblog.controllers;

import org.apache.logging.log4j.message.StringFormattedMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostController {

    @GetMapping("/posts")
    @ResponseBody
    public String postIndexPage(){
        return "posts index page";
    }

    @GetMapping("/posts/{id}")
    @ResponseBody
    public String viewPostId(@PathVariable long id) {
        return "Here is post #" + id +":";
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

    @GetMapping("/roll-dice")
    public String rollDice(){
        return "roll-dice";
    }

} // End PostController Class
