package com.validation;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class DataContainerTest extends AbstracValidatorTest {
    
    @Test
    public void testDataContainer() {
        Person person = new Person();
        person.setHobbies(new ArrayList<String>());
        person.getHobbies().add(" ");
        person.getHobbies().add(" ");
        person.getHobbies().add(" ");
        person.getHobbies().add(" ");
        person.getHobbies().add(" ");
        person.getHobbies().add("Climbing");
        person.setFirstName("alliano");
        person.setLastNmae("Uchiha");
        person.setAddress(new Address("adasd", "asdasdasd", "asddasdasd"));
        validate(person);
    }
}
