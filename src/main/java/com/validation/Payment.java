package com.validation;

import org.hibernate.validator.constraints.LuhnCheck;
import org.hibernate.validator.constraints.Range;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class Payment {
    
    @NotBlank(message = "order id gaboleh kosong")
    private String orderId;

    /**
     * @Range adalah Constrain dari Hibernate Validator
     * fungsi @Range sendiri sama seperti @Min dan @Max pada
     * Bean Validation
     */
    @Range(min = 10_000L, max = 100_000_000, message = "amount gaboleh kurang dari 10.000 dan gaboleh lebih dari 100.000.000")
    @NotNull(message = "ammount gaboleh kosong")
    private Long amount;

    /**
     * @LunCheck adalah annotasi dari Hibbernate Validator
     * yang mana fungsi dari @LunCheck ini untuk memvalidasi 
     * sebuah nomer kartu keredit
     * 
     * dan sebenarnya masih banayak lagi constrain yang diberikan
     * oleh Hibbernate Validaor
     */
    @LuhnCheck(message = "kartu keredit tidak valid")
    @NotBlank(message = "kartu keredit gaboleh kosong")
    private String creditCard;

    public Payment() {
    }

    public Payment(String orderId, Long amount, String creditCard) {
        this.orderId = orderId;
        this.amount = amount;
        this.creditCard = creditCard;
    }

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
}
