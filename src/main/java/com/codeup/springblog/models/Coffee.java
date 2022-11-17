package com.codeup.springblog.models;
// This is a POJO (Plain Old Java Object)

import javax.persistence.*;

@Entity
@Table(name = "coffees")
public class Coffee {
    /* ################################## Instance Variables/ Properties ############################################ */

    @Id // Annotates {id} as Id (table) so {id} is now primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //
    private long id;

    // @Column sets the variable as a column. nullable = false sets it to NOT NULL
    // length sets the maximum characters (for example: VARCHAR(50) is the same as length = 50)
    @Column(nullable = false, length = 50)
    private String roast;

    @Column(nullable = false)
    private String origin;

    @Column(nullable = false, length = 100)
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

    public Coffee (String roast, String origin, String brand){
        this.roast = roast;
        this.origin = origin;
        this.brand = brand;
    }
                                /* ################## Constructors ################## */


                                /* #################### Getters #################### */
    public long getId(){return id;}

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

    public void setId(long id) {this.id = id;}

                                /* #################### Setters #################### */
}
