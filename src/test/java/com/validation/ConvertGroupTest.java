package com.validation;

import org.junit.jupiter.api.Test;

import com.validation.groups.VirtualAccountPaymentGroup;

public class ConvertGroupTest extends AbstracValidatorTest {

    @Test
    public void testConvertGroupFailed() {
        Payment payment = new Payment();
        payment.setOrderId("001");
        payment.setAmount(30_000L);
        payment.setCreditCard("4111111111111111");
        payment.setVirtualAccount("+62813");
        Customer customer = new Customer();
        payment.setCustomer(customer);

        validateWithGroups(payment, VirtualAccountPaymentGroup.class);
    }

    @Test
    public void testConvertGroupFailedSuccess() {
        Payment payment = new Payment();
        payment.setOrderId("001");
        payment.setAmount(30_000L);
        payment.setCreditCard("4111111111111111");
        payment.setVirtualAccount("+62813");
        Customer customer = new Customer();
        customer.setEmail("allianoanoanymous@gmail.com");
        customer.setName("Alliano");
        payment.setCustomer(customer);
        validateWithGroups(payment, VirtualAccountPaymentGroup.class);
    }
}
