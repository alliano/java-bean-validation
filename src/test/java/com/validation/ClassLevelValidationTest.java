package com.validation;

import org.junit.jupiter.api.Test;

public class ClassLevelValidationTest extends AbstracValidatorTest {
    
    @Test
    public void testClassLevelValidation() {
        Register register = new Register("Alliano", "uchica", "uchiha123");
        this.validate(register);
    }
}
