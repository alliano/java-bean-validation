package com.validation;

import org.junit.jupiter.api.Test;

import com.validation.groups.PaymentGroup;

public class GroupSequenceTest extends AbstracValidatorTest {
    
    @Test
    public void testGroupSequenceFaill() {
        Payment payment = new Payment();
        validateWithGroups(payment, PaymentGroup.class);
    }

    @Test
    public void testGroupSequenceSuccess() {
        Payment payment = new Payment();
        payment.setAmount(50_000_000L);
        payment.setCreditCard("4111111111111111");
        payment.setOrderId("001");
        payment.setVirtualAccount("+62813");
        validateWithGroups(payment, PaymentGroup.class);
    }
}
