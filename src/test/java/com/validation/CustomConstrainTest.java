package com.validation;

import org.junit.jupiter.api.Test;
import com.validation.groups.CreditCardPaymentGroup;
import com.validation.groups.VirtualAccountPaymentGroup;

public class CustomConstrainTest extends AbstracValidatorTest {
    
    @Test
    public void testCustomConstrainSccess() {
        Payment payment = new Payment("OOPSD112", 600_000_000L, "42222222222222", "0092", null);
        validateWithGroups(payment, VirtualAccountPaymentGroup.class, CreditCardPaymentGroup.class);
    }

    @Test
    public void testCustomConstrainFaill() {
        Payment payment = new Payment("oopsd112", 600_000_000L, "42222222222222", "0092", null);
        validateWithGroups(payment, VirtualAccountPaymentGroup.class, CreditCardPaymentGroup.class);
    }
}
