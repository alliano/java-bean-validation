package com.validation.constrains;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Target({ElementType.METHOD, ElementType.CONSTRUCTOR})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {CheckPasswordParameterValidator.class})
public @interface CheckPasswordParameter {
    
    public int passwordParam();

    public int retypePasswordParam();

    public String message() default "password and retype password must be same";
    
    public Class<?>[] groups() default {};

    public Class<? extends Payload>[] payload() default {};
}
