package com.validation.services;

import com.validation.constrains.CheckPasswordParameter;

import jakarta.validation.constraints.NotBlank;

public class UserService {
    
    @CheckPasswordParameter(passwordParam = 1, retypePasswordParam = 2)
    public void register(
        @NotBlank(message = "username is required") String username,
        @NotBlank(message = "password is required") String password,
        @NotBlank(message = "retype password is required") String retypePassword) {

            // Logic register
    }
}
