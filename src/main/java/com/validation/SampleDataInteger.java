package com.validation;

import com.validation.containers.DataInteger;

import jakarta.validation.constraints.Min;

public class SampleDataInteger {
    
    @Min(value = 5, message = "must be greater or equal to 5")
    private DataInteger dataInteger;

    public DataInteger getDataInteger() {
        return dataInteger;
    }

    public void setDataInteger(DataInteger dataInteger) {
        this.dataInteger = dataInteger;
    }
}
