package com.codeup.springblog.models;

import com.codeup.springblog.repositories.PostRepository;

import javax.persistence.*;

@Entity
@Table(name = "Posts")
public class Post {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(nullable = false, length = 2000)
    private String body;

    ///////////////// Getters and Setters /////////////////////

    // Getter Id
    public long getId() {
        return id;
    }


    // Getter Title
    public String getTitle(){
        return this.title;
    }

    // Getter Body
    public String getBody(){
        return this.body;
    }

    // Setter Id
    public void setId(long id) {
        this.id = id;
    }
    // Setter Title
    public void setTitle(String newTitle){
        this.title = newTitle;
    }
    // Setter Body
    public void setBody(String newBody){
        this.body = newBody;
    }

    ///////////////////// Constructors /////////////////////////

    public Post(){}

    public Post(String title, String body) {
        this.title = title;
        this.body = body;
    }

    public Post(PostRepository postDao, String title, String body){
        this.title = title;
        this.body = body;
    }
}
