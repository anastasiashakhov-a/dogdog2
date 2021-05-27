package com.example.dogdog2.Home;


public class Friends {
    private int id;
    private String name;

    private String description;
    private String[] src;

    private PictureOfFriend [] images;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getFriend_image_url(){
        if ((images == null) || (images.length == 0)){
            return null;
        }
        else{
            return images[0].src;
        }
    }

}
