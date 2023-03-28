package com.validation.constrains;

import com.validation.Register;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CheckPasswordValidator implements ConstraintValidator<CheckPassword, Register> {

    @Override
    public boolean isValid(Register register, ConstraintValidatorContext constraintValidatorContext) { 
        // kalo datanya kosong diabaikan biar annotasi @NotBlank yang handle
        if(register == null || register.getRetypePassword() == null) return true;

        boolean isValid = register.getPassword().equals(register.getRetypePassword());
        if(!isValid) {
            // ini untuk dissable atau menonaktifkan message default nya
            constraintValidatorContext.disableDefaultConstraintViolation();
            // ini unutk meng custom message default nya
            constraintValidatorContext.buildConstraintViolationWithTemplate("retype password is different with retype password")
            .addPropertyNode("retypePassword")
            .addConstraintViolation();
        }
        return isValid;
    }
    
}
