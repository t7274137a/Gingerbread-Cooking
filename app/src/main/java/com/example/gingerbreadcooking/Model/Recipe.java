package com.example.gingerbreadcooking.Model;

public class Recipe {
    String id ,title,url,readyInMinutes,servings;

    public Recipe(String id,String title,String url,String readyInMinutes,String servings){
        this.id=id;
        this.title=title;
        this.url=url;
        this.readyInMinutes=readyInMinutes;
        this.servings=servings;
    }

    public String getId() {
        return id;
    }


    public String getTitle() {
        return title;
    }



    public String getUrl() {
        return url;
    }



    public String getReadyInMinutes() {
        return readyInMinutes;
    }


    public String getServings() {
        return servings;
    }
}
