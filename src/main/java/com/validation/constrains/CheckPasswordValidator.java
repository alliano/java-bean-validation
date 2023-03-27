package com.validation.constrains;

import com.validation.Register;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CheckPasswordValidator implements ConstraintValidator<CheckPassword, Register> {

    @Override
    public boolean isValid(Register register, ConstraintValidatorContext arg1) { 
        // kalo datanya kosong diabaikan biar annotasi @NotBlank yang handle
        if(register == null || register.getRetypePassword() == null) return true;

        if(register.getPassword().equals(register.getRetypePassword())) return true;

        else

        return false;
    }
    
}
