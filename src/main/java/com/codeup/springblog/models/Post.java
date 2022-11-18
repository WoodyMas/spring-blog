package com.codeup.springblog.models;

public class Post {
    private String title;
    private String body;

    ///////////////// Getters and Setters /////////////////////

    // Getter Title
    public String getTitle(){
        return this.title = title;
    }

    // Getter Body
    public String getBody(){
        return this.body = body;
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
    public Post(){

    }
    public Post(String title, String body){
        this.title = title;
        this.body = body;
    }
}
