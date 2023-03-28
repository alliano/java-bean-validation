package com.validation;

import java.util.Set;

import org.junit.jupiter.api.Test;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.metadata.ConstraintDescriptor;

public class ConstrainDescriptorTest extends AbstracValidatorTest {
    
    @Test
    public void tesDescriptor() {
        Person person = new Person();
        Set<ConstraintViolation<Person>> violations = this.validator.validate(person);
        violations.forEach(violation -> {
            ConstraintDescriptor<?> constraintDescriptor = violation.getConstraintDescriptor();
            System.out.println("message template : "+constraintDescriptor.getMessageTemplate());
            System.out.println("annotation : "+constraintDescriptor.getAnnotation());
            System.out.println("payload : "+constraintDescriptor.getPayload());
            // dan masih banyak lagi informasi yang bisa kita dapatkan
        });
    }
}
