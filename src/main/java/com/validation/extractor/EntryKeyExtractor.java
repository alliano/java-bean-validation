package com.validation.extractor;

import com.validation.containers.Entry;

import jakarta.validation.valueextraction.ExtractedValue;
import jakarta.validation.valueextraction.ValueExtractor;

/**
 * disini kita meletakan @ExtractedValue pada
 * paramter pertama generic type pada Entry class
 * karena parameter generic pertama pada Entry class adalah
 * Key, dan kita inigin memberitahu cara extract key nya
 */
public class EntryKeyExtractor implements ValueExtractor<Entry<@ExtractedValue ?, ?>>{

    @Override
    public void extractValues(Entry<@ExtractedValue ?, ?> originalValuEntry, ValueReceiver receiver) {
        receiver.keyedValue(null, "key", originalValuEntry.getKey());
    }
    
}
