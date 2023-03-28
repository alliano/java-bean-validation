package com.validation.extractor;

import com.validation.containers.Entry;

import jakarta.validation.valueextraction.ExtractedValue;
import jakarta.validation.valueextraction.ValueExtractor;


/**
 * disini kita meletakan @ExtractedValue pada
 * paramter kedua generic type pada Entry class
 * karena parameter generic kedua pada Entry class adalah
 * Value, dan kita inigin memberitahu cara extract Value nya
 */
public class EntryValueExtractor implements ValueExtractor<Entry<?, @ExtractedValue ?>> {

    @Override
    public void extractValues(Entry<?, @ExtractedValue ?> originalValueEntry, ValueReceiver receiver) {
        receiver.keyedValue(null, "value", originalValueEntry.getValue());
    }
    
}
