package com.codeup.springblog.controllers;
import com.codeup.springblog.models.Post;
import com.codeup.springblog.models.User;
import com.codeup.springblog.repositories.PostRepository;
import com.codeup.springblog.repositories.UserRepository;
import com.codeup.springblog.services.SpringBlogUtilities;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
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
    public String postsLandingPage(){
        return "/posts/show";
    }

    @GetMapping("/posts/")
//    @ResponseBody
    public String allPosts(Model model){
        User loggedInUser =  (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        System.out.println();
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

    @GetMapping("/posts/{id}/edit")
    public String editPostId(@PathVariable long id, Model model){
        Post post = postDao.findById(id);
        model.addAttribute("postEdit", post);

        long currentUserId = SpringBlogUtilities.currentUser().getId();

        if (currentUserId == 0){
            return "redirect:/login";
        }
        Post post1 = postDao.findById(id);
        if (post1.getUser().getId() != currentUserId){
            return "redirect:/posts";
        }
        model.addAttribute("post", post);
            return "/posts/edit";
    }



    @PostMapping("/posts/{id}/edit")
    public String editPostIdForm(@ModelAttribute Post editPost){



        List<User> userList = userDao.findAll();
        postDao.save(editPost);
        return "redirect:/posts";
    }

    @GetMapping("/posts/create")
    public String viewPostCreateForm(Model model){

        model.addAttribute("post", new Post());
        List<User> userList = userDao.findAll();
        model.addAttribute(userList);
         return "/posts/create";
    }

    @PostMapping("/posts/create")
    public String createNewController(@ModelAttribute Post post){
    User loggedInUser = (User) SecurityContextHolder
            .getContext()
            .getAuthentication()
            .getPrincipal();

    long userId = loggedInUser.getId();
    loggedInUser = userDao.findUserById(userId);
        post.setUser(loggedInUser);
        postDao.save(post);
        return "redirect:/posts";
    }



///////////////////////// Constructor ////////////////////////////////
    public PostController(PostRepository postDao, UserRepository userDao){
        this.postDao = postDao;
        this.userDao = userDao;
    }

} // End PostController Class
