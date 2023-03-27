package com.validation;

import org.hibernate.validator.constraints.LuhnCheck;
import org.hibernate.validator.constraints.Range;

import com.validation.constrains.CheckOrderId;
import com.validation.groups.CreditCardPaymentGroup;
import com.validation.groups.VirtualAccountPaymentGroup;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.ConvertGroup;
import jakarta.validation.groups.Default;

public class Payment {
    
    /**
     * untuk mengrouping suatu field kita cukup tambahkan
     * paramter groups pada constrain nya dan diikuti dengan 
     * interface yang telah kita buat untuk flaging dalam
     * kasus ini adalah  VirtualAccountPaymentGroup dan
     * CreditCardPaymentGroup
     */
    @CheckOrderId(groups = {VirtualAccountPaymentGroup.class, CreditCardPaymentGroup.class})
    private String orderId;

    @Range(min = 10_000L, max = 100_000_000, message = "{order.amount.range}", groups = {VirtualAccountPaymentGroup.class, CreditCardPaymentGroup.class})
    @NotNull(message = "{payment.amount.notblank}", groups = {VirtualAccountPaymentGroup.class, CreditCardPaymentGroup.class})
    private Long amount;

    @LuhnCheck(message = "credit card is invalid", groups = {CreditCardPaymentGroup.class})
    @NotBlank(message = "{payment.creaditcard.notblank}", groups = {CreditCardPaymentGroup.class})
    private String creditCard;

    @NotBlank(message = "{payment.virtualaccount.notblank}", groups = {VirtualAccountPaymentGroup.class}, payload = {EmailErrorPayload.class})
    private String virtualAccount;

    /**
     * ini walaupun kita telah meng annotasi @Valid pada filed ini
     * akan tetapi field ini datanya tidak akan pernah di validasi oleh 
     * java bean vaidation dikarnakan field ini di flaging/grouping
     * dengan VirtualAccountPaymentGroup dan CreditCardPaymentGroup
     * sedangkan field data customer ini kita tidak grouping samasekali
     * maka field tersebut akan menggunakan flaging/groping Default
     * 
     * agar data dari field customer di validasi juga, kita harus mengkonversi 
     * flaging nya dengan menggunakan annotasi @ConvertGroup
     */
    @Valid
    @NotNull(message = "{payment.customer.notnull}", groups = {VirtualAccountPaymentGroup.class, CreditCardPaymentGroup.class}, payload = {EmailErrorPayload.class})
    @ConvertGroup(from = VirtualAccountPaymentGroup.class, to = Default.class)
    @ConvertGroup(from = CreditCardPaymentGroup.class, to =  Default.class)
    private Customer customer;

    public Payment(
            @NotBlank(message = "{order.id.notblank}", groups = { VirtualAccountPaymentGroup.class,CreditCardPaymentGroup.class }) String orderId,
            @Range(min = 10_000L, max = 100_000_000L, message = "{order.amount.range}", groups = { VirtualAccountPaymentGroup.class,CreditCardPaymentGroup.class })
            @NotNull(message = "{payment.amount.notblank}", groups = { VirtualAccountPaymentGroup.class, CreditCardPaymentGroup.class }) Long amount,
            @LuhnCheck(message = "{payment.creaditcard.invalid }", groups = CreditCardPaymentGroup.class) @NotBlank(message = "{payment.creaditcard.notblank}", groups = CreditCardPaymentGroup.class) String creditCard,
            @NotBlank(message = "{payment.virtualaccount.notblank}", groups = VirtualAccountPaymentGroup.class) String virtualAccount,
            @Valid @NotNull(message = "{payment.customer.notnull}", groups = {VirtualAccountPaymentGroup.class, CreditCardPaymentGroup.class}) @ConvertGroup(from = VirtualAccountPaymentGroup.class, to = Default.class) @ConvertGroup(from = CreditCardPaymentGroup.class, to =  Default.class) Customer customer) {
        this.orderId = orderId;
        this.amount = amount;
        this.creditCard = creditCard;
        this.virtualAccount = virtualAccount;
        this.customer = customer;
    }

    public Payment() { }

    

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }

    @Override
    public String toString() {
        return "Payment [orderId=" + orderId + ", amount=" + amount + ", creditCard=" + creditCard + "]";
    }

    public String getVirtualAccount() {
        return virtualAccount;
    }

    public void setVirtualAccount(String virtualAccount) {
        this.virtualAccount = virtualAccount;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
