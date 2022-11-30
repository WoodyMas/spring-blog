package com.codeup.springblog.models;

import javax.persistence.*;
import java.util.List;

// This is a POJO (Plain Old Java Object)
@Entity
@Table(name = "coffees")
public class Coffee {
    /* ################################## Instance Variables/ Properties ############################################ */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;



    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    // Many (Current Class) to ONE  Coffee to ONE Supplier
    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    @ManyToMany(mappedBy = "coffeeList")
    private List<Customer> customers;

    private String roast;
    private String origin;

    private String brand;

    /* ################################################ Methods ##################################################### */

                                /* ################## Constructors ################## */
    public Coffee() {
    }

    public Coffee(String roast, String brand) {
        this.roast = roast;
        this.brand = brand;
    }

    public Coffee (String brand) {
        this.brand = brand;
    }
                                /* ################## Constructors ################## */


                                /* #################### Getters #################### */
    public String getRoast() {
        return roast;
    }

    public String getOrigin() {
        return origin;
    }

    public String getBrand(){
        return brand;
    }
                                /* #################### Getters #################### */

                                /* #################### Setters #################### */
    public void setRoast(String roast) {
        this.roast = roast;
    }


    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
                                /* #################### Setters #################### */
}
