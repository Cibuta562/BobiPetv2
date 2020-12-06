package com.example.insights;

import java.util.HashMap;
import java.util.Map;

/**
 * user class to keep track of registered user
 * and their data (profile info)
 */

public class Member {

    private String Name;
    private String Age;
    private String Interest;
    private String Productive;
    private String Motivated;
    private String Goal;

    public Member() {}

    public String getNames() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }

    public String getInterest() {
        return Interest;
    }

    public void setInterest(String interest) {
        Interest = interest;
    }

    public String getProductive() {
        return Productive;
    }

    public void setProductive(String productive) {
        Productive = productive;
    }

    public String getMotivated() {
        return Motivated;
    }

    public void setMotivated(String motivated) {
        Motivated = motivated;
    }

    public String getGoal() {
        return Goal;
    }

    public void setGoal(String goal) {
        Goal = goal;
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








}
