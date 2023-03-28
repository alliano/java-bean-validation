package com.validation.constrains;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.constraintvalidation.SupportedValidationTarget;
import jakarta.validation.constraintvalidation.ValidationTarget;

/**
 * disini kita harus tambahkan annotasi @SupportedValidationTarget
 * agar constrain validation yang kita buat mendukung validasi pada
 * level paramter, entah itu di method atau constructor
 */
@SupportedValidationTarget(value = {ValidationTarget.PARAMETERS})
public class CheckPasswordParameterValidator implements ConstraintValidator<CheckPasswordParameter, Object[]> {

    private int password;

    private int retypePassword;
    

    @Override
    public void initialize(CheckPasswordParameter constraintAnnotation) {
        this.password = constraintAnnotation.passwordParam();
        this.retypePassword = constraintAnnotation.retypePasswordParam();
    }

    @Override
    public boolean isValid(Object[] arg0, ConstraintValidatorContext arg1) {
        String password = (String) arg0[this.password];
        String retypePassword = (String) arg0[this.retypePassword];
        // jika password dan retypaswornya null skipp validation biarin aja @NotBlank yang handle
        if(password == null || retypePassword == null) return true;
        /**
         * jikak password dan retype password nya sama maka akan mereturn true jika tidak sama 
         * akan meretrun false
         */
        return retypePassword.equals(password);
    }
}
