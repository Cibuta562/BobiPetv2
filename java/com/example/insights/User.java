package com.example.insights;

import java.util.HashMap;
import java.util.Map;

/**
 * user class to keep track of registered user
 * and their data (profile info)
 */

public class User {

    private String name;
    private String age;
    private String interest;
    private String productive;
    private String motivated;
    private String goal;

    public User() {}
    

    public User(String name, String age, String interest,
                String productive, String motivated, String goal) {
        this.name = name;
        this.age = age;
        this.interest = interest;
        this.productive = productive;
        this.motivated = motivated;
        this.goal = goal;
    }

/*    public HashMap<String, Object> getAsMap(){
        HashMap<String, Object> userAsMap = new HashMap<>();
        userAsMap.put("username",username);
        userAsMap.put("password",password);
        userAsMap.put("age",age);
        userAsMap.put("name",name);
        //Add or remove more key value pair
        return userAsMap;
    }*/



    public void setname(String name) {
        this.name = name;
    }

    public void setage(String age) {
        this.age = age;
    }

    public void setinterest(String wordplace) {
        this.interest = wordplace;
    }

    public void setproductive(String productive) {
        this.productive = productive;
    }

    public void setmotivated (String motivated) {
        this.motivated = motivated;
    }

    public void setgoal(String goal) {
        this.goal = goal;
    }

    public String getname() {
        return name;
    }

    public String getage() {
        return age;
    }

    public String getinterest() {
        return interest;
    }

    public String getproductive() {
        return productive;
    }

    public String getmotivated () {
        return motivated;
    }

    public String getgoal() {
        return goal;
    }




}
