package com.validation;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

public class NestedValidationTest {
    
    private Validator validator;

    @BeforeEach
    public void setUp() {
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    public void testNestedValidation() {
        Person person = new Person();
        person.setFirstName("alliano");
        person.setLastNmae("alfarez");

        Address address = new Address();

        person.setAddres(address);

        Set<ConstraintViolation<Person>> violations = this.validator.validate(person);

        for (ConstraintViolation<Person> violation : violations) {
            System.out.println("message : "+violation.getMessage()); 
            System.out.println("message : "+violation.getPropertyPath());
        }
    }
}
