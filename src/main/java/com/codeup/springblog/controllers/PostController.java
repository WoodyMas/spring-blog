package com.codeup.springblog.controllers;
import com.codeup.springblog.models.Post;
import com.codeup.springblog.models.User;
import com.codeup.springblog.repositories.PostRepository;
import com.codeup.springblog.repositories.UserRepository;
import com.codeup.springblog.services.EmailService;
import com.codeup.springblog.services.SpringBlogUtilities;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PostController {
    private final PostRepository postDao;
    private final UserRepository userDao;

    private final EmailService emailService;

    @GetMapping("/splash") // The original display with no edit function
    public String postsLandingPage(Model model){
        List<Post> allPostsForGuest = postDao.findAll();
        model.addAttribute("posts", allPostsForGuest);
        return "/posts/splash"; // returning splash.html
    }

    @GetMapping("/posts")
    public String guestLandingAllPosts(Model model) {
        List<Post> allGuestPosts = postDao.findAll();
        model.addAttribute("posts", allGuestPosts);
        return "/posts/guest-post-view";
    }


//    @GetMapping("/allPosts")
//    public String guestLandingAllPosts(Model model) {
//        List<Post> allGuestPost
//    }

    @GetMapping("/posts/") // Display with edit functionality
    public String allPosts(Model model){

//        User loggedInUser = SpringBlogUtilities.currentUser();
        List<Post> allPosts = postDao.findAll();
        model.addAttribute("posts", allPosts);

        long currentUserId = SpringBlogUtilities.currentUser().getId();

        if (currentUserId == 0){
            return "redirect:/posts";
        } else {
            return "/posts/show";

        }


    }

    @GetMapping("/posts/{id}")
//    @ResponseBody
    public String viewPostId(@PathVariable long id, Model model) {
        Post postView = postDao.findById(id);
        model.addAttribute("aPost", postView);

        return "/posts/post-index";
    }

    @GetMapping("/posts/{id}/edit")
    public String editPostId(@PathVariable long id, Model model){
        Post post = postDao.findById(id);
        model.addAttribute("postEdit", post);

        long currentUserId = SpringBlogUtilities.currentUser().getId();

        if (currentUserId == 0){
            return "redirect:/login";
        }
//        Post post1 = postDao.findById(id);
        if (post.getUser().getId() != currentUserId){
            return "redirect:/posts"; // return guest Landing page
        }
        model.addAttribute("post", post);
            return "/posts/edit"; // returning edit.html
    }



    @PostMapping("/posts/{id}/edit")
    public String editPostIdForm(@ModelAttribute Post editPost){

        List<User> userList = userDao.findAll();
        postDao.save(editPost);
        return "redirect:/posts/";
    }
//    @GetMapping("/posts/{id}/delete")
//    public String deletePost(@PathVariable long id, Model model) {
//        Post selectedPost = postDao.findById(id);
//        model.addAttribute("postDelete", selectedPost);
//
//        return "/posts/edit";
//    }

    @GetMapping("/posts/{id}/delete")
    public String deletePost(@PathVariable long id, Post post){
//        User user = SpringBlogUtilities.currentUser();
//        post.setUser(user);
        post = postDao.findById(id);
        postDao.delete(post);
        return "redirect:/posts/";
    }

//    @PostMapping("/posts/{id}/delete")
//    public String deletePostId(@ModelAttribute Post deletePost) {
//        List<User> userList = userDao.findAll();
//        postDao.delete(deletePost);
//        return "redirect:/posts/";
//    }



    @GetMapping("/profile")
    public String allPostsBlog(Model model){

        User user = userDao.findUserById(SpringBlogUtilities.currentUserProfile());
        List<Post> blog = user.getUserposts();
        model.addAttribute("blogs", blog);
        return "/posts/show";
    }

    @GetMapping("/posts/create")
    public String viewPostCreateForm(Model model){

        model.addAttribute("post", new Post());
        List<User> userList = userDao.findAll();
//        model.addAttribute(userList);
         return "/posts/create"; // returning create.html
    }

    @PostMapping("/posts/create")
    public String createNewController(@ModelAttribute Post post){
    User loggedInUser = SpringBlogUtilities.currentUser();
//    long userId = loggedInUser.getId();
//    loggedInUser = userDao.findUserById(userId);
        post.setUser(loggedInUser);
        emailService.prepareAndSend(loggedInUser, post.getTitle(), post.getBody());
        emailService.prepareAndSend(loggedInUser, loggedInUser.getUsername(), loggedInUser.getName());
        postDao.save(post);
        return "redirect:/posts/"; // redirecting to postsLandingPage(){};
    }


    @GetMapping("/my-tools")
    public String htmlTools(Model model) {
        Post postTools = postDao.findById(SpringBlogUtilities.currentUser().getId());
        model.addAttribute("allLoggedInPosts", postTools);
        return "/partials/database-partials-users-posts";
    }

    @PostMapping("/my-tools")
    public String htmlToolsView(Model model) {
        User currentUser = SpringBlogUtilities.currentUser();
        List<Post> allUserPosts = currentUser.getUserposts();
        model.addAttribute("allUserPosts", allUserPosts);
        return "/partials/database-partials-users-posts";

    }

///////////////////////// Constructor ////////////////////////////////
    public PostController(PostRepository postDao, UserRepository userDao, EmailService emailService){
        this.postDao = postDao;
        this.userDao = userDao;
        this.emailService = emailService;
    }

} // End PostController Class
