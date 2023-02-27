package com.codeup.springblog.models;

import javax.persistence.*;
import java.util.List;

@Entity
//@ManyToOne
@Table(name = "user")

public class User {
    // Adding admin privileges will require attention that I cannot allocate at the moment

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String email;

//    @ManyToOne
//    @JoinColumn(name = "user_posts")
//    private Post posts;

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Post> getUserposts() {
        return userposts;
    }

    public void setUserposts(List<Post> userposts) {
        this.userposts = userposts;
    }

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, length = 1000)
    private String password;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Post> userposts;
    ////////////////////////////////////// End Instance Variables/ Properties (Columns)

    ///////////////////////// Getters & Setters
    public String getName(){
        return username;
    }
    public void setName(String name){
        this.username = name;
    }
    public long getId(){
        return id;
    }
    public void setId(long id){
        this.id = id;
    }

    public String getEmail(){
        return email;
    }


    ///////////////////////// Constructors
    public User(){};

    public User(String name) {
        this.username = name;
    }
    public User(String name, String email, String password){
        this.username = name;
        this.email = email;
        this.password = password;
    }

    public User(long id, String email, String username, String password, List<Post> userposts) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
//        this.userposts = userposts;
    }

    public User(long id, String username, String email, String password){
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
    }
}
