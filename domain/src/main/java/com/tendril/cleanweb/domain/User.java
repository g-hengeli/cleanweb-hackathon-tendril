package com.tendril.cleanweb.domain;

/**
 */
public class User {

    public String firstName;
    public String lastName;
    public String userName;
    public String zipCode;


    public User() {
    }

    public User(String firstName, String lastName, String userName, String zipCode) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}
