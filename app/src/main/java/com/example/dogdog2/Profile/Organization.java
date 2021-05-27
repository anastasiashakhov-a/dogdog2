package com.example.dogdog2.Profile;



public class Organization {

    private int term_id;
    private String name;


    private String description;

    private Object [] brand_image;

    public int getId() {
        return term_id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getBrande_image_url(){
        return brand_image[0].toString();
    }

}
