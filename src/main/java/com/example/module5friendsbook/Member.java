package com.example.module5friendsbook;

public class Member {
    String name;
    String sex;
    String mobile;

    Member(String n, String s, String m){
        name = n;
        sex = s;
        mobile = m;
    }

//    getters and setters

    public String toString(){
        return name;
    }
}
