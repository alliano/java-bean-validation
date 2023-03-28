package com.validation;

import com.validation.containers.Entry;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class SampleEntry {
    
    @NotNull
    private Entry<@NotBlank(message = "key is required") String, @NotBlank(message = "value is required") String> entryData;

    public Entry<String, String> getEntryData() {
        return entryData;
    }

    public void setEntryData(Entry<String, String> entryData) {
        this.entryData = entryData;
    }
}
