package com.validation;

import java.util.List;

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

    private java.util.List<@NotBlank(message = "hobbie is required") String> hobbies;

    /**
     * disini kita annotasi dengan annotasi constrain semua parameternya
     * agar nanti di validasi oleh bean validation
     * @param firstName
     * @param lastNmae
     * @param address
     */

    public Person(
            @NotBlank(message = "nama depan gaboleh kosonk") @Size(max = 20, message = "nama depan gaboleh lebih dari 20 karakter") String firstName,
            @NotBlank(message = "nama blakang gaboleh kosonk") @Size(max = 20, message = "nama blakang gaboleh lebih dari 20 karakter") String lastNmae,
            @NotNull(message = "allamat gaboleh kosonk") @Valid Address address,
            List<@NotBlank(message = "hobbie is required") String> hobbies) {
        this.firstName = firstName;
        this.lastNmae = lastNmae;
        this.address = address;
        this.hobbies = hobbies;
    }


    @Valid
    public Person() {}
    
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public java.util.List<String> getHobbies() {
        return hobbies;
    }

    public void setHobbies(java.util.List<String> hobbies) {
        this.hobbies = hobbies;
    }
}
