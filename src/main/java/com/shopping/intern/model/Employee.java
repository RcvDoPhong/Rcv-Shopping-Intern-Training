package com.shopping.intern.model;

public class Employee {

    private long id;
    private String first_name;
    private String last_name;
    private String email_address;

    public Employee(String first_name, String last_name, String email_address) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email_address = email_address;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return this.id;
    }

    public void setFirstName(String first_name) {
        this.first_name = first_name;
    }

    public String getFirstName() {
        return this.first_name;
    }

    public void setLastName(String last_name) {
        this.last_name = last_name;
    }

    public String getLastName() {
        return this.last_name;
    }

    public void setEmailAddress(String email_address) {
        this.email_address = email_address;
    }

    public String getEmailAddress() {
        return this.email_address;
    }

    @Override
    public String toString() {
        return getId() + ", " + getFirstName() + " " + getLastName() + ", " + getEmailAddress();
    }
}
