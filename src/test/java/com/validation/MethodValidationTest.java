package com.validation;

import java.lang.reflect.Method;
import java.util.Set;

import org.junit.jupiter.api.Test;

import jakarta.validation.ConstraintViolation;

public class MethodValidationTest extends AbstracValidatorTest {
    
    @Test
    public void testMethodValidationParameter() throws NoSuchMethodException, SecurityException {
        Person person = new Person();

        String name = "";

        Method method = Person.class.getMethod("greeting", String.class);

        Set<ConstraintViolation<Person>> violations = this.executableValidator.validateParameters(person, method, new Object[]{name});
        /**
         * violation nya akan memiliki value dikarnakan varible name diatas
         * tidak ada value nya
         */
        for (ConstraintViolation<Person> violation : violations) {
            System.out.println(violation.getPropertyPath());
            System.out.println(violation.getMessage());
            System.out.println("----------------------");
        }
    }

    @Test
    public void testMethodValidationReturnValue() throws NoSuchMethodException, SecurityException {
        Person person = new Person();
        person.setFirstName("");
        person.setLastNmae("");

        Method method = person.getClass().getMethod("fullName");
        String returnValue = person.fullName();
        /**
         * violation akan memiliki value karena terjadi error validasi
         * error validasi terjadi karena kita nga ngeset firstname dan lastname nya
         * jadi pas method fullName di panggil method tersebut
         * mengembalikan value null
         */
        Set<ConstraintViolation<Person>> violations = this.executableValidator.validateReturnValue(person, method, returnValue);
        for (ConstraintViolation<Person> violation : violations) {
            System.out.println(violation.getPropertyPath());
            System.out.println(violation.getMessage());
            System.out.println("---------------");
        }
    }
}
