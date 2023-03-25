package com.validation;

import org.junit.jupiter.api.Test;

import com.validation.groups.CreditCardPaymentGroup;
import com.validation.groups.VirtualAccountPaymentGroup;

public class GroupTest extends AbstracValidatorTest {
    
    @Test
    public void testCreditCardPaymentGroupFaill() {
        Payment payment = new Payment();
        payment.setAmount(20_000L);
        payment.setCreditCard("011");
        payment.setOrderId("001");
        payment.setVirtualAccount("+628");
        
        validateWithGroups(payment, CreditCardPaymentGroup.class);
        
    }
    @Test
    public void testCreditCardPaymentGroupSuccess() {
        Payment payment = new Payment();
        payment.setAmount(20_000L);
        payment.setCreditCard("4111111111111111");
        payment.setOrderId("001");
        payment.setVirtualAccount("+628");
        /**
         * secara degault jikau constrain pada class payment itu kita 
         * grouping maka Vaidator tidak akan memvalidasi nya
         * agar validator memvalidasinya kita harus tambahakan paramter grouping pada 
         * saat kita validasi.
         * 
         * jikalau kita hanya meberikan 1 groub saja misalnya VirtualAccountPaymentGroup
         * maka field yang akan di validasi hanya field yang memiliki group VirtualAccountPaymentGroup
         * saja
         */
        validateWithGroups(payment,VirtualAccountPaymentGroup.class, CreditCardPaymentGroup.class);
    }
}
