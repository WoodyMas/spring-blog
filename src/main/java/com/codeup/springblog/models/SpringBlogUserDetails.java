package com.codeup.springblog.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class SpringBlogUserDetails extends User implements UserDetails {

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    // No Arg Constructor
    public SpringBlogUserDetails(){}

    public SpringBlogUserDetails(String name) {
        super(name);
    }

    public SpringBlogUserDetails(String name, String email, String password) {
        super(name, email, password);
    }

    ;

    public SpringBlogUserDetails(long id, String email, String username, String password, List<Post> userposts) {
        super(id, email, username, password, userposts);
    }

    public SpringBlogUserDetails(long id, String username, String email, String password){
        super(id, username, email, password);
    }
}
