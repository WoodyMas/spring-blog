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

    @Column(nullable = false)
    private String username;

    @Column(nullable = false, length = 1000)
    private String password;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Post> userposts;
    ////////////////////////////////////// End Instance Variables/ Properties (Columns)


}
