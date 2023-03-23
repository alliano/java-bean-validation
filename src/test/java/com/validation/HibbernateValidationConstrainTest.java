package com.validation;

import org.junit.jupiter.api.Test;

public class HibbernateValidationConstrainTest extends AbstracValidatorTest {
    
    @Test
    public void testHibernateVaidationCOnstrain() {
        Payment payment = new Payment();
        payment.setAmount(1000L);
        payment.setCreditCard("0092");
        payment.setOrderId("001");
        
        validate(payment);
    }

    @Test
    public void testHibernateVaidationConstrainFalid() {
        Payment payment = new Payment();
        payment.setAmount(80_000L);
        payment.setCreditCard("4111111111111111");
        payment.setOrderId("001");
        validate(payment);
    }
}
