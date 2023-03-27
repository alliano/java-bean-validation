package com.validation.constrains;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.validation.enums.CaseMode;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
/**
 * ini untuk menentukan target dari annotasi 
 * ini field, atau method, dan lain lain
 */
@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE})
/**
 * ini untuk menentukan annotasi ini 
 * di eksekusi nya kapan, saat Runtime
 * atau yang lainya
 */
@Retention(RetentionPolicy.RUNTIME)
/**
 * ini untuk menentukan class mana
 * yang akan memvalidasi annotasi ini, simpelnya
 * adalah class yang akan menyimpan logic dari
 * annotasi ini
 */
@Constraint(validatedBy = {CheckCaseValidator.class})
public @interface CheckCase {
    
    /**
     * ini untuk mengambil setingan yang akan diberikan nanti
     * apakah tipenya CaseMode.UPPERCASE, atau CaseMode.LOWERCASE
     *  */ 
    CaseMode mode();

    /**
     * ini untuk message error nya
     * @return
     */
    String message() default "valu is invalid";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
