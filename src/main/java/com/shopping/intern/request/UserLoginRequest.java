package com.shopping.intern.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.lang.Nullable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginRequest {

    // Tại sao không dùng @NotEmpty mà lại dùng @NotBlank ??
    @NotNull
    @NotBlank
    @Email
    private String email;

    @NotNull
    @NotBlank
    @Size(min = 6, message = "Password must be greater or equal to 6 characters")
    private String password;

    @Nullable
    private boolean remember;
}

