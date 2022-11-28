package com.codeup.springblog.models;

import javax.persistence.*;
import java.util.List;

@Entity
//@ManyToOne
@Table(name = "user")

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String email;

//    @ManyToOne
//    @JoinColumn(name = "user_posts")
//    private Post posts;

    @Column(nullable = false)
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
}
