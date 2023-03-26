package com.validation.constrains;

import com.validation.enums.CaseMode;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CheckCaseValidator implements ConstraintValidator<CheckCase, String> {

    private CaseMode caseMode;
    
    @Override
    public void initialize(CheckCase constraintAnnotation) {
        this.caseMode = constraintAnnotation.mode();
    }

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
