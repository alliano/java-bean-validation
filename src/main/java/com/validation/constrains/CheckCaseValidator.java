package com.validation.constrains;

import com.validation.enums.CaseMode;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CheckCaseValidator implements ConstraintValidator<CheckCase, String> {

    private CaseMode caseMode;
    
    /**
     * method ini digunakan untuk mengambil pilihan case nya
     * CaseMode.UPPERCASE atau CaseMode.LOWERCASE
     */
    @Override
    public void initialize(CheckCase constraintAnnotation) {
        this.caseMode = constraintAnnotation.mode();
    }

    /**
     * method ini digunakan untuk mengecek apakah 
     * data yang di inputkan valid atau nga
     */
    @Override
    public boolean isValid(String arg0, ConstraintValidatorContext arg1) {
        if(arg0 == null) return false;

        if(this.caseMode == CaseMode.UPPERCASE) {
            return arg0.equals(arg0.toUpperCase());
        }
        else if(this.caseMode == CaseMode.LOWERCASE) {
            return arg0.equals(arg0.toLowerCase());
        }
        return false;
    }
}
