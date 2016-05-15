package com.sucuk.sucuk;

/**
 * Created by eralpsahin on 5/15/2016.
 */
public class User {
    private String uname;
    private String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }
    public String toString()
    {
        return uname+" "+role;
    }
    public User() {
    }
}
