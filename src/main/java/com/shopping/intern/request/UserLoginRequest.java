package com.shopping.intern.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.lang.Nullable;

public class UserLoginRequest {

    // Tại sao không dùng @NotEmpty mà lại dùng @NotBlank ??
    // @NotNull
    // @NotBlank
    // @Email
    private String email;

    // @NotNull
    // @NotBlank
    // @Size(min = 6, message = "Password must be greater or equal to 6 characters")
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
