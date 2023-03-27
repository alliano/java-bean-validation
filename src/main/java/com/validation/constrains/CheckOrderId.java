package com.validation.constrains;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.validation.enums.CaseMode;
import com.validation.groups.CreditCardPaymentGroup;
import com.validation.groups.VirtualAccountPaymentGroup;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@CheckCase(mode = CaseMode.UPPERCASE, message = "{orderid.uppercase.invalid}", groups = {VirtualAccountPaymentGroup.class, CreditCardPaymentGroup.class})
@NotBlank(message = "{order.id.notblank}", groups = {VirtualAccountPaymentGroup.class, CreditCardPaymentGroup.class})
@Size(max = 10, min = 1, message = "{order.id.size}", groups = {VirtualAccountPaymentGroup.class, CreditCardPaymentGroup.class})
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Documented @Constraint(validatedBy = {})
public @interface CheckOrderId {
    
    String message() default "invalid value";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
