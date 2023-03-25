package com.validation;

import java.util.Set;

import org.junit.jupiter.api.Test;

import com.validation.groups.VirtualAccountPaymentGroup;

import jakarta.validation.ConstraintViolation;

public class PayloadTest extends AbstracValidatorTest {

    @Test
    public void testPayload() {
        Payment payment = new Payment();
        payment.setOrderId("001");
        payment.setAmount(50_000L);
        payment.setCreditCard("4111111111111111");
        Customer customer = new Customer();
        customer.setEmail("allianoanonymous@gmail.com");
        customer.setName("alliano");
        payment.setCustomer(customer);
        /**
         * disini kita tidak meng set nila dari fild virtual account
         * harapanya nanti akan terjadi error lalu error tersebut kita akan 
         * ambil payload nya dan mentriger nya
         */
       Set<ConstraintViolation<Payment>> violations = this.validator.validate(payment, VirtualAccountPaymentGroup.class);
       for (ConstraintViolation<Payment> violation : violations) {
           System.out.println("error field : "+violation.getPropertyPath());
           violation.getConstraintDescriptor().getPayload().forEach( aClass -> {
                if(aClass == EmailErrorPayload.class) {
                    EmailErrorPayload emailErrorPayload = new EmailErrorPayload();
                    emailErrorPayload.sendEmail(violation);
                }
           });
       }

    }
}
