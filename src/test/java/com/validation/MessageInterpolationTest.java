package com.validation;

import org.junit.jupiter.api.Test;

import com.validation.groups.VirtualAccountPaymentGroup;

public class MessageInterpolationTest extends AbstracValidatorTest {
    @Test
    public void testMessageInterpolation() {
        Payment payment = new Payment();
        payment.setAmount(500_000_000L);
        payment.setCreditCard("347237432479973");
        payment.setOrderId("");
        payment.setVirtualAccount("");
        validateWithGroups(payment, VirtualAccountPaymentGroup.class);
    }
}
