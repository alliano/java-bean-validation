package com.validation;

import org.junit.jupiter.api.Test;

import com.validation.groups.VirtualAccountPaymentGroup;

public class ConstrainCompositionTest extends AbstracValidatorTest {
    
    @Test
    public void testConstrainComposition() {
        Payment payment = new Payment(null, null, null, null, null);
        validateWithGroups(payment, VirtualAccountPaymentGroup.class);
    }
}
