package com.TourismAgencySystem.Model;

public class User {
    private int id;
    private String uname;
    private String pass;
    private String name_lastname;
    private String role;

    public User(int id, String uname, String pass, String name_lastname, String role) {
        this.id = id;
        this.uname = uname;
        this.pass = pass;
        this.name_lastname = name_lastname;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getName_lastname() {
        return name_lastname;
    }

    public void setName_lastname(String name_lastname) {
        this.name_lastname = name_lastname;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
