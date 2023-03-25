package com.validation;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Person {

    @NotBlank(message = "nama depan gaboleh kosonk")
    @Size(max = 20, message = "nama depan gaboleh lebih dari 20 karakter")
    private String firstName;
    
    @NotBlank(message = "nama blakang gaboleh kosonk")
    @Size(max = 20, message = "nama blakang gaboleh lebih dari 20 karakter")
    private String lastNmae;
    
    @NotNull(message = "allamat gaboleh kosonk")
    /**
     * jika kita ingin object address nya di validasi
     * maka kita harus meng annotasi dengan annotasi @Valid,
     * karna jikalau kita tidak annotasi dengan @Valid 
     * by default object address tidak akan pernah di validasi
     * jadi meng annotasi dengan @NotBlank saja tidak cukup
     */
    @Valid
    private Address address;

    public Person() {}

    public Person(String firstName, String lastNmae, Address address) {
        this.firstName = firstName;
        this.lastNmae = lastNmae;
    }

    public Address getAddress() {
        return this.address;
    }

    public void setAddres(Address address) {
        this.address = address;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastNmae() {
        return lastNmae;
    }

    public void setLastNmae(String lastNmae) {
        this.lastNmae = lastNmae;
    }

    @Override
    public String toString() {
        return "Person [firstName=" + firstName + ", lastNmae=" + lastNmae + "]";
    }

    
    /**
     * saat kita ingin memvalidasi suatu method kita terlebih dahulu 
     * megannotasi method tersebut dengan annotation constrain
     * pada contoh kali ini paramter dan return value nya di annotasi
     * dengan annotasi constrain
     * @param name
     */
    public void greeting(@NotBlank(message = "name can't be blank") String name) {
        System.out.println("Hello my name ".concat(name));
    }

    @NotBlank(message = "full name can't be blank")
    public String fullName() {
        return this.firstName+" "+this.lastNmae;
    }
}
