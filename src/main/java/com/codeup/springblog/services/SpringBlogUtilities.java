package com.codeup.springblog.services;

import com.codeup.springblog.models.User;
import org.springframework.security.core.context.SecurityContextHolder;

public class SpringBlogUtilities {

    public static User currentUser(){
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }



    public static long currentUserProfile(){
        return ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
    }

}