package com.codeup.springblog.models;

// This is a POJO (Plain Old Java Object)
public class Coffee {
    /* ################################## Instance Variables/ Properties ############################################ */
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
