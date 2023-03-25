package com.validation;

import org.hibernate.validator.constraints.LuhnCheck;
import org.hibernate.validator.constraints.Range;

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
    @NotBlank(message = "order id can't be blank", groups = {VirtualAccountPaymentGroup.class, CreditCardPaymentGroup.class})
    private String orderId;

    @Range(min = 10_000L, max = 100_000_000, message = "amount can't les than 10.000 and can't more than 100.000.000", groups = {VirtualAccountPaymentGroup.class, CreditCardPaymentGroup.class})
    @NotNull(message = "amount can't be blank", groups = {VirtualAccountPaymentGroup.class, CreditCardPaymentGroup.class})
    private Long amount;

    @LuhnCheck(message = "credit card is invalid", groups = {CreditCardPaymentGroup.class})
    @NotBlank(message = "credit card can't be blank", groups = {CreditCardPaymentGroup.class})
    private String creditCard;

    @NotBlank(message = "virtual account can't be blank", groups = {VirtualAccountPaymentGroup.class})
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
    @NotNull(message = "customer can't be null", groups = {VirtualAccountPaymentGroup.class, CreditCardPaymentGroup.class})
    @ConvertGroup(from = VirtualAccountPaymentGroup.class, to = Default.class)
    @ConvertGroup(from = CreditCardPaymentGroup.class, to =  Default.class)
    private Customer customer;

    public Payment(
            @NotBlank(message = "order id can't be blank", groups = { VirtualAccountPaymentGroup.class,CreditCardPaymentGroup.class }) String orderId,
            @Range(min = 10_000L, max = 100_000_000L, message = "amount can't les than 10.000 and can't more than 100.000.000", groups = { VirtualAccountPaymentGroup.class,CreditCardPaymentGroup.class })
            @NotNull(message = "amount can't be blank", groups = { VirtualAccountPaymentGroup.class, CreditCardPaymentGroup.class }) Long amount,
            @LuhnCheck(message = "credit card is invalid", groups = CreditCardPaymentGroup.class) @NotBlank(message = "credit card can't be blank", groups = CreditCardPaymentGroup.class) String creditCard,
            @NotBlank(message = "virtual account can't be blank", groups = VirtualAccountPaymentGroup.class) String virtualAccount,
            @Valid @NotNull(message = "customer can't be null", groups = {VirtualAccountPaymentGroup.class, CreditCardPaymentGroup.class}) @ConvertGroup(from = VirtualAccountPaymentGroup.class, to = Default.class) @ConvertGroup(from = CreditCardPaymentGroup.class, to =  Default.class) Customer customer) {
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
