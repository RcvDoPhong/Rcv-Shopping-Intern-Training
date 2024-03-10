package com.shopping.intern.request;

public class UserLoginRequest {

    private String email;

    private String password;

    private boolean remember;

    public UserLoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    public boolean isRemember() {
        return this.remember;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRemember(boolean remember) {
        this.remember = remember;
    }

    @Override
    public String toString() {
        return this.email + " " + this.password;
    }
}
