package com.validation;

import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

public class ConstrainViolationTest {
    
    private ValidatorFactory falidatorFactory;

    private Validator validator;

    @BeforeEach
    public void setUp() {
        this.falidatorFactory = Validation.buildDefaultValidatorFactory();
        this.validator = this.falidatorFactory.getValidator();
    }

    @AfterEach
    public void tearDown() {
        this.falidatorFactory.close();
    }

    @Test
    public void testConstrainViolationFailed() {
        Person person = new Person();
        /**
         * saat kita memanggil validate(person), maka 
         * validator aka memvalidasi oject person, jikalau
         * terjadi error validasi maka error tersebut akan masuk ke
         * constrainViolation,
         * 
         * jadi, secara sigkatnya jikalau variabel constrainViolations 
         * itu ada isinya alias tidak null maka itu tidak error
         * dan jikalau pada variabel constrainViolations itu ada isinya
         * maka itu terjadi error.
         */
        Set<ConstraintViolation<Person>> constrainViolations = this.validator.validate(person);
        Assertions.assertEquals(2, constrainViolations.size());
        for (ConstraintViolation<Person> constrainViolation : constrainViolations) {
            System.out.println(constrainViolation.getMessage());
        
        }
    }

    @Test
    public void testObjectMetadata() {
        Person person = new Person();
        Set<ConstraintViolation<Person>> constrainViolations = this.validator.validate(person);
       for (ConstraintViolation<Person> constrainViolation : constrainViolations) {
            // untuk mendapatkan persan error
            System.out.println("Message : "+constrainViolation.getMessage());
            // untuk mendapatkan object yang di validasi
            System.out.println("Bean : "+constrainViolation.getLeafBean());
            // untuk mendapatkan annotasi yang menyebabkan error
            System.out.println("Constrain : "+constrainViolation.getConstraintDescriptor().getAnnotation());
            // untuk mendapatkan nilai yang tidak valid
            System.out.println("Invalid value : "+constrainViolation.getInvalidValue());
            // untuk mendapatkan field yang terjadi error validasi
            System.out.println("Path : "+constrainViolation.getPropertyPath());
       }
    }


}
