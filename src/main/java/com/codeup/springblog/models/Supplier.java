package com.codeup.springblog.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "suppliers")
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    public List<Coffee> getCoffees() {
        return coffees;
    }

    public Supplier(String name, List<Coffee> coffees) {
        this.name = name;
        this.coffees = coffees;
    }

    public void setCoffees(List<Coffee> coffees) {
        this.coffees = coffees;
    }

    // Deleting a supplier will cascade to all coffees (and all coffees will be deleted)
    @OneToMany(cascade = CascadeType.ALL)
    private List <Coffee> coffees;


    ///////////////////////// Getters & Setters
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public long getId(){
        return id;
    }
    public void setId(long id){
        this.id = id;
    }

    ///////////////////////// Constructors
    public Supplier(){};

    public Supplier(String name) {
        this.name = name;
    }


}
