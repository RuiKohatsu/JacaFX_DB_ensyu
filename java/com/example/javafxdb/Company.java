package com.example.javafxdb;

public class Company {
    private int id;
    private String name;

    public Company(int id, String name){
        this.id = id;
        this.name = name;

    }

    public int getId() {
        return id;
    }

    public String getCompany() {
        return name;
    }


    public void setId(int id){
        this.id = id;
    }

    public void setCompany(String name){
        this.name = name;
    }


}
