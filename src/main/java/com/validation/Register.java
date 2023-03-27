package com.validation;

import com.validation.constrains.CheckPassword;

import jakarta.validation.constraints.NotBlank;

@CheckPassword(message = "password and retype password must be same")
public class Register {
    
    @NotBlank(message = "username is required")
    private String username;

    @NotBlank(message = "password is required")
    private String password;

    @NotBlank(message = "retype password is required")
    private String retypePassword;

    public Register(
            @NotBlank(message = "username is required") String username,
            @NotBlank(message = "password is required") String password,
            @NotBlank(message = "retype password is required") String retypePassword) {
        this.username = username;
        this.password = password;
        this.retypePassword = retypePassword;
    }
    public Register() {
    
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRetypePassword() {
        return retypePassword;
    }

    public void setRetypePassword(String retypePassword) {
        this.retypePassword = retypePassword;
    }
    @Override
    public String toString() {
        return "Register [username=" + username + ", password=" + password + ", retypePassword=" + retypePassword + "]";
    }
}
