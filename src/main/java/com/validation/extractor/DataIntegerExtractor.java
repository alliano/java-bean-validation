package com.validation.extractor;

import com.validation.containers.DataInteger;

import jakarta.validation.valueextraction.ExtractedValue;
import jakarta.validation.valueextraction.UnwrapByDefault;
import jakarta.validation.valueextraction.ValueExtractor;

@UnwrapByDefault
public class DataIntegerExtractor implements ValueExtractor<@ExtractedValue(type = Integer.class) DataInteger> {

    @Override
    public void extractValues(@ExtractedValue(type = Integer.class) DataInteger oroginalValue, ValueReceiver receiver) {
        receiver.value(null, oroginalValue.getInteger());
    }
}
