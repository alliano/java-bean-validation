package com.validation.groups;

import jakarta.validation.GroupSequence;

/**
 * jadi jikalau nanti terjadi error validasi pada 
 * group CreditCardPaymentGroup maka 
 * group selanjutnya tidak akan di eksekusi
 */
@GroupSequence(value = {
    CreditCardPaymentGroup.class,
    VirtualAccountPaymentGroup.class
})
public interface PaymentGroup { }
