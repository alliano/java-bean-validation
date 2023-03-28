package com.validation;

import java.lang.reflect.Method;
import java.util.Set;

import org.junit.jupiter.api.Test;

import com.validation.services.UserService;

import jakarta.validation.ConstraintViolation;

public class CrossParameterValidationTest extends AbstracValidatorTest {
    
    @Test
    public void testCrossParameter() throws NoSuchMethodException, SecurityException {
        
        UserService userService = new UserService();

        String username = "Alliano";

        String password = "uchiha itachi";
        
        String retypePassword = "Fugaku";

        Method method = userService.getClass().getMethod("register", String.class, String.class, String.class);

        Set<ConstraintViolation<UserService>> violations = this.executableValidator.validateParameters(userService, method, new Object[] {username, password, retypePassword});

        violations.forEach(violation -> {
            System.out.println("Error param : "+violation.getPropertyPath());
            System.out.println("Error message : "+violation.getMessage());
            System.out.println("=================");
        });

    }
}
