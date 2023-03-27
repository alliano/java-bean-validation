package com.validation.constrains;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Constraint(validatedBy = {CheckPasswordValidator.class})
/**
 * untuk menjadikan annotasi ini bisa digunakan di level class
 * kita harus tambahkan ElementType.TYPE
 */
@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckPassword {
    
    public String message() default "retrype password must be same with password";

    public Class<?>[] groups() default {};

    public Class<? extends Payload>[] payload() default {};
}
