package com.validation;

import com.validation.containers.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class SampleData {
    
    @NotNull(message = "data is required")
    private Data<@NotBlank(message = "data cant be blank") @Size(max = 5, min = 2,message = "data can't more than 5 characters and can't less than 2 characters") String> data;

    public Data<String> getData() {
        return this.data;
    }

    public void setData(Data<String> data) {
        this.data = data;
    }
}
