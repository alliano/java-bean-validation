package com.validation;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Payload;

public class EmailErrorPayload implements Payload {
    
    public void sendEmail(ConstraintViolation<?> violation) {
        System.out.println("payload trigered when error : "+violation.getMessage());
    }
}
