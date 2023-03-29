package com.validation;

import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import com.validation.extractor.DataExtractor;
import com.validation.extractor.DataIntegerExtractor;
import com.validation.extractor.EntryKeyExtractor;
import com.validation.extractor.EntryValueExtractor;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.MessageInterpolator;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.executable.ExecutableValidator;

public abstract class AbstracValidatorTest {
    
    protected ValidatorFactory validatorFactory;

    protected Validator validator;

    protected ExecutableValidator executableValidator;

    protected MessageInterpolator messageInterpolator;

    @BeforeEach
    public void setUp() {
        this.validatorFactory = Validation.byDefaultProvider()
                                .configure().addValueExtractor(new DataExtractor())
                                .addValueExtractor(new EntryKeyExtractor())
                                .addValueExtractor(new EntryValueExtractor())
                                .addValueExtractor(new DataIntegerExtractor())
                                .buildValidatorFactory();
        this.validator = this.validatorFactory.getValidator();
        this.executableValidator = validator.forExecutables();
        this.messageInterpolator = this.validatorFactory.getMessageInterpolator();
    }

    @AfterEach
    public void tearDown() {
        this.validatorFactory.close();
    }

    public void validate(Object object) {
        Set<ConstraintViolation<Object>> violations = this.validator.validate(object);
        for (ConstraintViolation<Object> violation : violations) {
            System.out.println("Error message : "+violation.getMessage());
            System.out.println("Error Field : "+violation.getPropertyPath());
        }
    }
    public void validateWithGroups(Object object, Class<?> ...groups) {
        Set<ConstraintViolation<Object>> violations = this.validator.validate(object, groups);
        for (ConstraintViolation<Object> violation : violations) {
            System.out.println("Error message : "+violation.getMessage());
            System.out.println("Error Field : "+violation.getPropertyPath());
        }
    }
}
