package com.validation;

import java.util.Locale;
import java.util.Set;

import org.hibernate.validator.internal.engine.MessageInterpolatorContext;
import org.hibernate.validator.messageinterpolation.ExpressionLanguageFeatureLevel;
import org.junit.jupiter.api.Test;

import com.validation.groups.VirtualAccountPaymentGroup;

import jakarta.validation.ConstraintViolation;

public class MessageInternationalizationTest extends AbstracValidatorTest {
    
    @Test
    public void testMessageInternationalization() {
        Payment payment = new Payment();
        payment.setAmount(500_000_000L);
        payment.setCreditCard("214072347932");
        payment.setCustomer(null);
        payment.setVirtualAccount(null);
        payment.setOrderId("94873374274638");
        /**
         * sebelum kita validasi kita ubah terlebih dahulu Locale default ny
         */
        Locale.setDefault(Locale.of("id", "ID"));
        Set<ConstraintViolation<Payment>> violations = this.validator.validate(payment, VirtualAccountPaymentGroup.class);
        violations.forEach(violation -> {
            System.out.println("Error message : "+violation.getMessage());
            System.out.println("Error filed : "+violation.getPropertyPath());
            System.out.println("================");
        });
    }

    @Test
    public void testMessageInternationalizationInterpolator() {
        Payment payment = new Payment();
        payment.setAmount(500_000_000L);
        payment.setCreditCard("");
        payment.setCustomer(null);
        payment.setVirtualAccount(null);
        payment.setOrderId("");
        Set<ConstraintViolation<Payment>> violations = this.validator.validate(payment, VirtualAccountPaymentGroup.class);
        violations.forEach(violation -> {
           MessageInterpolatorContext messageInterpolatorContext = new MessageInterpolatorContext(
                violation.getConstraintDescriptor(), violation.getInvalidValue(), violation.getRootBeanClass(),
                violation.getPropertyPath(), violation.getConstraintDescriptor().getAttributes(),
                violation.getConstraintDescriptor().getAttributes(), ExpressionLanguageFeatureLevel.VARIABLES,
                true);
         String message = this.messageInterpolator.interpolate(violation.getMessageTemplate(), messageInterpolatorContext, Locale.of("id", "ID"));
         System.out.println(message);
        }); 
    }
}
