package com.tendril.cleanweb.domain;

/**
 */
public class User {

    public String firstName;
    public String lastName;
    public String username;
    public String zipCode;


    public User() {
    }

    public User(String firstName, String lastName, String username, String zipCode) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.zipCode = zipCode;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}
