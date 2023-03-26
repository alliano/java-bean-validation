package com.validation;

import java.lang.reflect.Constructor;
import java.util.Set;

import org.junit.jupiter.api.Test;

import jakarta.validation.ConstraintViolation;

public class ConstructorValidationTest extends AbstracValidatorTest {
    
    @Test
    public void testConstructorValidatorParameter() throws NoSuchMethodException, SecurityException {
        Person person = new Person();
        String firstName = "";
        String lastName = "";
        Address address = new Address();
        //ini kita ambil construcor nya dari class person
        Constructor<? extends Person> constructor = person.getClass().getConstructor(String.class, String.class, Address.class);
        /**
         * ini kita validasi constructor nya menggunakan method executableValidator
         * dengan method validateConstructorParameters(), yang menerima tiga paramterer
         * paremter pertama adalah consturctor yang akan di validasi,
         * parameter ke dua adalah parameter dari constructor yang akan kita validasi
         */
        Set<ConstraintViolation<Person>> violations = this.executableValidator.validateConstructorParameters(constructor, new Object[]{firstName, lastName, address});
        violations.forEach( violation -> {
            System.out.println("Field Error : "+violation.getPropertyPath());
            System.out.println("Error Message : "+violation.getMessage());
            System.out.println("============================");
        });
    }

    @Test
    public void testConstructorValidatorReturnValue() throws NoSuchMethodException, SecurityException {
        Person person = new Person("", "", new Address());
        Constructor<Person> constructor = Person.class.getConstructor(String.class, String.class, Address.class);
        Set<ConstraintViolation<Person>> violations = executableValidator.validateConstructorReturnValue(constructor, person);
        violations.forEach(violation -> {
            System.out.println("Field Error : "+violation.getPropertyPath());
            System.out.println("Error Message : "+violation.getMessage());
            System.out.println("============================");   
        });
    }
}
