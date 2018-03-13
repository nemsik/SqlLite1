package com.example.bartek.sqllite1;

import java.util.ArrayList;

/**
 * Created by bartek on 13.03.2018.
 */

public class Person {
    private long id;
    private String name;
    private String age;
    private String occupation;
    private String image;
    //private ArrayList<String> latitude;
    //private ArrayList<String> longitude;

    public Person() {
    }

    public Person(String name, String age, String occupation, String image) {
        this.name = name;
        this.age = age;
        this.occupation = occupation;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
