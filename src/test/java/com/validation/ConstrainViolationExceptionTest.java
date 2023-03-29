package com.validation;

import java.util.Set;

import org.junit.jupiter.api.Test;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

public class ConstrainViolationExceptionTest extends AbstracValidatorTest {
    
    @Test
    public void testConstrainViolationException() {
        Person person = new Person();
        Set<ConstraintViolation<Person>> violations = this.validator.validate(person);
        if(!violations.isEmpty()) throw new ConstraintViolationException(violations);
    }
}
